package Fx;


import entities.AccountantOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import restDao.AccountantOrderRestDao;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class AccountantController implements Initializable {

    @FXML
    private TextField kilometers;
    @FXML
    private TextField pricePerKilometer;
    @FXML
    private CheckBox hazardous;
    @FXML
    private Button calculateBtn;
    @FXML
    private TextArea price;
    @FXML
    private Button clearBtn;



    @FXML
    private TableColumn<AccountantOrder, Integer> orderid;
    @FXML
    private TableColumn<AccountantOrder, Integer> customerid;
    @FXML
    private TableColumn<AccountantOrder, Double> amount;
    @FXML
    private TableColumn<AccountantOrder, String> desAddress;
    @FXML
    private TableColumn<AccountantOrder, Integer> desPostCode;
    @FXML
    private TableColumn<AccountantOrder, String> pickupAdd;
    @FXML
    private TableColumn<AccountantOrder, LocalDate> deliverydate;
    @FXML
    private TableColumn<AccountantOrder, Boolean> hazardousT;
    @FXML
    private TableColumn<AccountantOrder, String> email;
    @FXML
    private TableView table;


    @FXML
    private TextArea textarea;
    @FXML
    private TextField emailt;
    @FXML
    private Button sendb;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateBtn.setOnAction(this::handleButtonAction);
        clearBtn.setOnAction(this::handleClearButtonEvent);

        //the uncommented lines below were causing issues to load into account page.

        //sendb.setOnAction(this::handleSendButton);

        orderid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        desAddress.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
        desPostCode.setCellValueFactory(new PropertyValueFactory<>("destinationPostcode"));
        //pickupAdd.setCellValueFactory(new PropertyValueFactory<>("pickupAddress"));
        deliverydate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        hazardousT.setCellValueFactory(new PropertyValueFactory<>("hazardous"));
        //email.setCellValueFactory(new PropertyValueFactory<>("email"));

        AccountantOrderRestDao a = new AccountantOrderRestDao();
        Collection<AccountantOrder> list = null;
        try {
            list = a.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setItems((ObservableList) list);

    }

    ObservableList<AccountantOrder> TestController = FXCollections.observableArrayList();



    ObservableList<AccountantOrder> getAcc() {
        ObservableList<AccountantOrder> list = FXCollections.observableArrayList();
        list.add(new AccountantOrder(1, 1, 3000.00, "Street123", 123456,
                "Pickup123", LocalDate.parse("2002-12-12"), true, "D@test", 250.00));
        list.add(new AccountantOrder(2, 1, 3000.00, "Street123", 123456,
                "Pickup123", LocalDate.parse("2002-12-12"), true, "D@test", 250.00));

        return list;
    }


    @FXML
    public void handleSendButton(ActionEvent e1) {
        String text = textarea.getText();
        emailt.setText("" + text);
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

        //checkbox get event
        boolean hazardousSelected = hazardous.isSelected();

        //possible to put calc in Calculator class?

        //type cast String input into double
        double d = Double.parseDouble(kms);
        double d1 = Double.parseDouble(pricePerKilometers);

        //type cast boolean into double
        //one so that end result is not 0
        //1.5 describes the percentage for a hazardous liquid
        double d2 = (hazardousSelected) ? 1.5 : 1;

        double res = d * d1 * d2;
        //round result to two places after comma
        res = Math.round(100.0 * res) / 100.0;

        price.setText("" + res);
    }


    //inner class AlertHelper
    public static class AlertHelper {
        public static void showAlert(Alert.AlertType alertType, Window acc, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(acc);
            alert.show();
        }
    }


    //clear button
    @FXML
    private void handleClearButtonEvent(ActionEvent e) {
        kilometers.clear();
        kilometers.setText("");
        pricePerKilometer.clear();
        pricePerKilometer.setText("");
        price.clear();
        hazardous.setSelected(false);





    }


    // Accountant order

}
