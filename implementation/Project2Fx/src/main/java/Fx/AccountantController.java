package Fx;


import entities.CustomerOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import restDao.CustomerOrderRestDao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccountantController implements Initializable {


    @FXML
    private TextField id;
    @FXML
    private TextField amount1;
    @FXML
    private TextField mail;
    @FXML
    private TextField deliveryDate1;
    @FXML
    private TextField destinationAddress1;
    @FXML
    private TextField destinationPostCode1;
    @FXML
    private TextField pickUpLocation1;
    @FXML
    private TextField totalPrice;
    @FXML
    private CheckBox isHazardous1;
    @FXML
    private CheckBox isHazardous2;
    @FXML
    private Button sendbtn;


    // Calculator
    @FXML
    private TextField kilometers;
    @FXML
    private TextField pricePerKilometer;
    @FXML
    private Button calculateBtn;
    @FXML
    private TextArea price;
    @FXML
    private Button clearBtn;


    //Table name
    @FXML
    private TableView table;
    @FXML
    private TableColumn<CustomerOrder, Integer> orderId;
    @FXML
    private TableColumn<CustomerOrder, String> email;
    @FXML
    private TableColumn<CustomerOrder, String> pickUpLocation;
    @FXML
    private TableColumn<CustomerOrder, String> destinationAddress;
    @FXML
    private TableColumn<CustomerOrder, String> destinationPostCode;
    @FXML
    private TableColumn<CustomerOrder, Double> amount;
    @FXML
    private TableColumn<CustomerOrder, LocalDate> deliveryDate;
    @FXML
    private TableColumn<CustomerOrder, Boolean> isHazardous;


    //@FXML
    //private TableColumn<Order, String> totalPrice;
    //@FXML
    //private TableColumn<Order, String> status;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        calculateBtn.setOnAction(this::handleButtonAction);
        clearBtn.setOnAction(this::handleClearButtonEvent);
        sendbtn.setOnAction(this::handleSendButton);


        ObservableList<CustomerOrder> testController = FXCollections.observableArrayList();

        orderId.setCellValueFactory(new PropertyValueFactory<>("customerOrderId"));
        //customerId.setCellValueFactory(new PropertyValueFactory<>(""));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        pickUpLocation.setCellValueFactory(new PropertyValueFactory<>("pickUpAddress"));
        destinationAddress.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
        destinationPostCode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        isHazardous.setCellValueFactory(new PropertyValueFactory<>("hazardous"));
        //totalPrice.


        //orderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        //orderStatus.setText();
        //orderStatus.setText(String.valueOf(OrderStatus.INPROGRESS));

        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
        Optional<Collection<CustomerOrder>> maybeList = customerOrderRestDao.getAll();

        if (maybeList.isPresent()) {
            table.setItems((ObservableList<CustomerOrder>) maybeList.get());
            System.out.println(maybeList);

        } else {
            System.out.println("Error could not retrieve data from backend (AccountantController)");
        }


        table.setRowFactory(t -> {
            TableRow<CustomerOrder> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    CustomerOrder rowData = row.getItem();

                    id.setText(String.valueOf(row.getItem().getCustomerOrderId()));
                    mail.setText(row.getItem().getEmail());
                    amount1.setText(String.valueOf(row.getItem().getAmount()));
                    deliveryDate1.setText(String.valueOf(row.getItem().getDeliveryDate()));
                    destinationAddress1.setText(String.valueOf(row.getItem().getDestinationAddress()));
                    destinationPostCode1.setText(String.valueOf(row.getItem().getPostcode()));
                    pickUpLocation1.setText(String.valueOf(row.getItem().getPickUpAddress()));
                    isHazardous1.setSelected(row.getItem().isHazardous());
                    isHazardous2.setSelected(row.getItem().isHazardous());







                    System.out.println(rowData);
                }
            });
            return row;
        });

    }


    @FXML
    public void handleSendButton(ActionEvent actionEvent) {

       int id = Integer.parseInt(this.id.getText().trim());
        String email = this.mail.getText().trim();
        String pickUp = pickUpLocation1.getText();
        String destinationAdd = destinationAddress1.getText();
        String destionationPc = destinationPostCode1.getText();
        double amount = Double.parseDouble(amount1.getText().trim());
        LocalDate deliverydateValue = LocalDate.parse(deliveryDate1.getCharacters());
        Boolean hazardousSelectedOrder = isHazardous1.isSelected();



        double proposedPrice = Double.parseDouble(totalPrice.getText().trim());


        CustomerOrder customerOrder = new CustomerOrder(id, email,
                pickUp, destinationAdd, destionationPc, amount,
                deliverydateValue, hazardousSelectedOrder, proposedPrice);
        System.out.println("______________________________________________");
        System.out.println(customerOrder);


        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
        Optional<CustomerOrder> customer = customerOrderRestDao.get(id);

        System.out.println(customer.get());
        customerOrderRestDao.update(customer.get());

       customer = customerOrderRestDao.get(id);
        System.out.println(customer);





    }


        //calculate btn
    @FXML
    protected void handleButtonAction(ActionEvent e) {
        String kms = kilometers.getText();
        String pricePerKilometers = pricePerKilometer.getText();

        //pop up window with warning messages
        Window acc = calculateBtn.getScene().getWindow();
        if (kms.isEmpty() || pricePerKilometers.isEmpty() || kms.isEmpty() && pricePerKilometers.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, acc, "Warning", "Please make sure that all fields" +
                    " are filled out with numbers!");
            return;
        } else if (!kms.matches("[0-9]+") || !pricePerKilometers.matches("^\\d+(\\.\\d+)?")) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, acc, "Warning", "Please make sure you only type" +
                    " in numbers!");
            return;
        } else if (pricePerKilometers.equals("0") || kms.equals("0")) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, acc, "Warning", "Please make sure that fields " +
                    "are not 0!");
            return;
        }

        boolean hazardousSelected = isHazardous2.isSelected();

        double d = Double.parseDouble(kms);
        double d1 = Double.parseDouble(pricePerKilometers);
        double d2 = (hazardousSelected) ? 1.5 : 1;
        double res = d * d1 * d2;
        res = Math.round(100.0 * res) / 100.0;
        price.setText("" + res);
    }


    //clear button
    @FXML
    private void handleClearButtonEvent(ActionEvent e) {
        kilometers.clear();
        kilometers.setText("");
        pricePerKilometer.clear();
        pricePerKilometer.setText("");
        price.clear();
        isHazardous2.setSelected(false);
    }


    //inner class AlertHelper
    static class AlertHelper {

        public static void showAlert(Alert.AlertType alertType, Window acc, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(acc);
            alert.show();
        }
    }
}
