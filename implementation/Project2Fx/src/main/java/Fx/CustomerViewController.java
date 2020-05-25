package Fx;

import entities.AccountantOrder;

import entities.Customer;
import entities.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {


    //customer info
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private DatePicker birthdate;
    @FXML
    private TextField phonenr;
    @FXML
    private TextField address;


    //deliveryInfo
    @FXML
    private TextField pickuplocation;
    @FXML
    private TextField destinationaddress;
    @FXML
    private TextField destinationpostcode;
    @FXML
    private TextField amountintons;
    @FXML
    private DatePicker deliverydate;
    @FXML
    private CheckBox hazardouscus;
    @FXML
    private Button sendbutton;


    //does not do anything yet -> store data in db

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendbutton.setOnAction(this::handleSendButtonAction);
    }

    @FXML
    protected void handleSendButtonAction(ActionEvent e) {
        String pickUp = pickuplocation.getText();
        String destinationAdd = destinationaddress.getText();
        String destionationPc = destinationpostcode.getText();
        double amount = Double.parseDouble(phonenr.getText().trim());
        LocalDate desiredDeliveryDate = deliverydate.getValue();
        Boolean hazardousSelectedOrder = hazardouscus.isSelected();


        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        String item = name.getText().trim();
        String item1 = email.getText().trim();
        //String item = birthdate.
        LocalDate item2 = birthdate.getValue();
        int item3 = Integer.parseInt(phonenr.getText().trim());

        String item4 = address.getText().trim();
        Order o = new Order(pickUp, destinationAdd, destionationPc, amount, desiredDeliveryDate, hazardousSelectedOrder);
        Customer a = new Customer(item, item1, item2, item3, item4);

        customers.add(a);
        orders.add(o);




        System.out.println(o);
        System.out.println(customers);


    }

   /* public void whenPostJsonUsingHttpClient_thenCorrect() throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com/%22);

         String json = "{"id":1,"name":"John"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }*/
    //TODO whenever the send order button is pressed the details shall be stored in the db
    //ToDO retireve data from db and display them in account order table






        /*Window cus = sendbutton.getScene().getWindow();
        if (customerName.isEmpty() || customerEmail.isEmpty() || customerPhonenumber.isEmpty() || customerAddress.isEmpty()) {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, cus, "Warning", "Please " +
                    "make sure that all fields are filled out with numbers!");
            return;
        }

        if (pickUp.isEmpty() || destinationAdd.isEmpty() || destionationPc.isEmpty() || amount.isEmpty()) {
            CustomerViewController.AlertHelper.showAlert(Alert.AlertType.WARNING, cus, "Warning", "Please " +
                    "make sure that all fields are filled out with numbers!");
            return;
        }*/


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
    }}


