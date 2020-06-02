package Fx;

import entities.CustomerOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import restDao.CustomerOrderRestDao;

import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    //customer info
    @FXML
    private TextField name1;
    @FXML
    private TextField name2;
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
    private TableColumn<CustomerOrder, Double> totalPriceCus;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<CustomerOrder> testController1 = FXCollections.observableArrayList();

        orderId.setCellValueFactory(new PropertyValueFactory<>("customerOrderId"));
        //customerId.setCellValueFactory(new PropertyValueFactory<>(""));
        emailRow.setCellValueFactory(new PropertyValueFactory<>("email"));
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
            table1.setItems((ObservableList<CustomerOrder>) maybeList.get());
            System.out.println(maybeList);

        } else {
            System.out.println("Error could not retrieve data from backend (AccountantController)");
        }

    }

    @FXML
    protected void handleSendButtonAction(ActionEvent e) {

        String fName = name1.getText().trim();
        String lName = name2.getText().trim();
        String email = this.email.getText().trim();
        LocalDate birthDate = birthdate.getValue();
        String phoneNumber = phonenr.getText().trim();
        String address = this.address.getText().trim();

        String pickUp = pickuplocation.getText();
        String destinationAdd = destinationaddress.getText();
        String destionationPc = destinationpostcode.getText();
        double amount = Double.parseDouble(amountintons.getText().trim());
        LocalDate deliverydateValue = deliverydate.getValue();
        Boolean hazardousSelectedOrder = hazardouscus.isSelected();
        double proposedPrice = 0.0;
        String orderStatus = "";


        CustomerOrder customerOrder = new CustomerOrder(998, fName, lName,
                birthDate, email, phoneNumber, address,
                pickUp, destinationAdd, destionationPc, amount,
                deliverydateValue, hazardousSelectedOrder, proposedPrice, orderStatus);


        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();

        customerOrderRestDao.save(customerOrder);


        System.out.println(customerOrder);


    }


}


