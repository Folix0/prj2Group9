package Fx;

import businessLogic.CustomerLogic;
import entities.CustomerOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import restDao.*;

import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    //customer info
    @FXML
    private TextField firstNameTf;
    @FXML
    private TextField lastNameTf;
    @FXML
    private TextField emailTf;
    @FXML
    private DatePicker birthdateTf;
    @FXML
    private TextField phinenumberTf;
    @FXML
    private TextField addressTf;
    //deliveryInfo
    @FXML
    private TextField pickupLocationTf;
    @FXML
    private TextField destinationAddressTf;
    @FXML
    private TextField destinationpostcodeTf;
    @FXML
    private TextField amountInLitresTf;
    @FXML
    private DatePicker deliveryDateTf;


    //Quotation Table
    @FXML
    private CheckBox hazardouscus;


    //Display Order in quotation
    @FXML
    private Button sendbutton;
    @FXML
    private TableView table1;
    @FXML
    private TableColumn<CustomerOrder, String> emailRow;
    @FXML
    private TableColumn<CustomerOrder, String> pickUpLocation;
    @FXML
    private TableColumn<CustomerOrder, String> destinationAddress;
    @FXML
    private TableColumn<CustomerOrder, String> destinationPostCode;
    @FXML
    public TableColumn<CustomerOrder, Double> amount;
    @FXML
    private TableColumn<CustomerOrder, LocalDate> deliveryDate;
    @FXML
    private TableColumn<CustomerOrder, Boolean> isHazardous;
    @FXML
    private TableColumn<CustomerOrder, Integer> orderId;
    @FXML
    private TableColumn<CustomerOrder, Double> totalPrice;
    @FXML
    private TableColumn<CustomerOrder, String> customerOrderStatus;


    //instance variable of cusotmer logic class
    CustomerLogic customerLogic;


//TODO insert a condition -> when true the order status must be set in the correct state automatically

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insertDataInTable();
        sendbutton.setOnAction(this::handleSendButtonAction);
        //should assure that the btn can be clicked only one
        //TODO also do it for the other button
    }


    //from initialize in this method inserted
    //called method in initialize
    @FXML
    private void insertDataInTable() {
        orderId.setCellValueFactory(new PropertyValueFactory<>("customerOrderId"));
        emailRow.setCellValueFactory(new PropertyValueFactory<>("email"));
        pickUpLocation.setCellValueFactory(new PropertyValueFactory<>("pickUpAddress"));
        destinationAddress.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
        destinationPostCode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        isHazardous.setCellValueFactory(new PropertyValueFactory<>("hazardous"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("proposedPrice"));
        customerOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));


        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
        Optional<Collection<CustomerOrder>> maybeList = customerOrderRestDao.getAll();

        if (maybeList.isPresent()) {
            table1.setItems((ObservableList<CustomerOrder>) maybeList.get());
            System.out.println(maybeList);

        } else {
            System.out.println("Error could not retrieve data from backend (AccountantController)");
        }
    }


    /**
     * @param event
     * when the button is clicked the information from the elemetns are gotten
     * put the elements in object
     * instantiate logic class and put created object as parameter
     * when the input fails the check from the logic a warning window is opened
     * delegate the rest to save method from customer rest dao and pass the newly created object as parameter
     */
    //when set onaction in scenebuilder you cannot call method in initialize -> dont need to set on action
    @FXML
    protected void handleSendButtonAction(ActionEvent event) {
        String fName = firstNameTf.getText().trim();
        String lName = lastNameTf.getText().trim();
        String email = this.emailTf.getText().trim();
        LocalDate birthDate = birthdateTf.getValue();
        String phoneNumber = phinenumberTf.getText().trim();
        String address = this.addressTf.getText().trim();

        String pickUp = pickupLocationTf.getText();
        String destinationAdd = destinationAddressTf.getText();
        String destionationPc = destinationpostcodeTf.getText();
        Double amount = Double.parseDouble(amountInLitresTf.getText().trim());
        LocalDate deliverydateValue = deliveryDateTf.getValue();
        Boolean hazardousSelectedOrder = hazardouscus.isSelected();
        Double proposedPrice = 0.0;
        String orderStatus = "Pending";

        CustomerOrder customerOrder = new CustomerOrder(998, fName, lName,
                birthDate, email, phoneNumber, address,
                pickUp, destinationAdd, destionationPc, amount,
                deliverydateValue, hazardousSelectedOrder, proposedPrice, orderStatus);

        customerLogic = new CustomerLogic(customerOrder);

        Window window = sendbutton.getScene().getWindow();
        if (!customerLogic.lastnameCheck()) {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, window, "Warning",
                    "Last name has to start with a capital letter and cannot be empty");
            return;
            
        } else if (!customerLogic.firstnameCheck()) {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, window, "Warning",
                    "First name has to start with a capital letter and cannot be empty");
            return;
        }
        if (!customerLogic.birthdateCheck()) {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, window, "Warning",
                    "Birth date has to be in the past");
            return;
        }
        if (!customerLogic.checkDeliveryDate()) {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, window, "Warning",
                    "Delivery date has to be at least 14 days after the current date");
            return;
        }
        if (!customerLogic.checkAmount()) {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, window, "Warning",
                    "Amount has to be in the range 20000.00 to 50000.00 liters");
            return;
        }

        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
        customerOrderRestDao.save(customerOrder);

        System.out.println(customerOrder);
    }

        //always displays error message from fname


      /*
        else if (!phoneNumber.matches("^[\\+\\d]?(?:[\\d-.\\s()]*)$"))
        {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, window, "Warning",
                    "Phone number field can only include numbers and plus sign");
            return;
        }
       // else if ()*/


    /**
     * needed for the popup windows
     */
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

    @FXML
    public void acceptButton(ActionEvent actionEvent) {

        System.out.println("1");

        System.out.println("2");

        CustomerOrder customerOrder = (CustomerOrder) table1.getSelectionModel().getSelectedItem();
        System.out.println("3");

        if(customerOrder.getOrderStatus().equals("Approved")) {
            customerOrder.setOrderStatus("Accepted");

        System.out.println(customerOrder.getOrderStatus());

        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
        System.out.println("5");
        customerOrderRestDao.update(customerOrder);
        System.out.println("6");
        } else {
            System.out.println("SKAP");
        }
    }

    @FXML
    public void declineButton(ActionEvent actionEvent) {
        System.out.println("1");

        System.out.println("2");

        CustomerOrder customerOrder = (CustomerOrder) table1.getSelectionModel().getSelectedItem();
        System.out.println("3");

        if(customerOrder.getOrderStatus().equals("Approved")) {
            customerOrder.setOrderStatus("Rejected");

            System.out.println(customerOrder.getOrderStatus());

            CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
            System.out.println("5");
            customerOrderRestDao.update(customerOrder);
            System.out.println("6");
        } else {
            System.out.println("SKAPrejected");
        }
    }


}


