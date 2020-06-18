package Fx;


import businessLogic.AccountantLogic;
import entities.Calculator;
import entities.CustomerOrder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import restDao.CustomerOrderRestDao;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccountantController implements Initializable {


    @FXML
    private TextField orderIdTf;
    @FXML
    private TextField amountTf;
    @FXML
    private TextField mailTf;
    @FXML
    private TextField deliveryDateTf;
    @FXML
    private TextField destinationAddressTf;
    @FXML
    private TextField destinationPostcodeTf;
    @FXML
    private TextField pickupLocationTf;
    @FXML
    private TextField totalPriceTf;
    @FXML
    private CheckBox isHazardousCbQuotation;
    @FXML
    private CheckBox isHazardousCalculator;
    @FXML
    private Button sendbtn;
    @FXML
    private Button rejectbtn;


    // Calculator
    @FXML
    private TextField kilometersCalculatorTf;
    @FXML
    private TextField pricePerKilometerCalculatorTf;
    @FXML
    private Button calculateBtn;
    @FXML
    private TextArea priceTextAreaCalculator;
    @FXML
    private Button clearBtn;


    //Table name
    @FXML
    private TableView<CustomerOrder> table;
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
    @FXML
    private TableColumn<CustomerOrder, String> orderStatus;


    //instance from logic
    private AccountantLogic accountantLogic = new AccountantLogic();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateBtn.setOnAction(this::handleButtonAction);
        clearBtn.setOnAction(this::handleClearButtonEvent);
        sendbtn.setOnAction(this::handleSendButton);
        rejectbtn.setOnAction(this::handleRejectButton);
        setDataInTable();
        fillTextFields();
    }

    /**
     * fills the table with data from the database
     */
    @FXML
    private void setDataInTable() {
        orderId.setCellValueFactory(new PropertyValueFactory<>("customerOrderId"));
        //customerId.setCellValueFactory(new PropertyValueFactory<>(""));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        pickUpLocation.setCellValueFactory(new PropertyValueFactory<>("pickUpAddress"));
        destinationAddress.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
        destinationPostCode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        isHazardous.setCellValueFactory(new PropertyValueFactory<>("hazardous"));
        orderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        //proposedPrice.setCellValueFactory(new PropertyValueFactory<>("proposedPrice"));

        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
        Optional<Collection<CustomerOrder>> maybeList = customerOrderRestDao.getAll();

        if (maybeList.isPresent())
        {
            table.setItems((ObservableList<CustomerOrder>) maybeList.get());
            System.out.println(maybeList);

        }
        else {
            System.out.println("Error could not retrieve data from backend (AccountantController)");
        }
    }

    /**
     * when the a row from the table is selected the textfields are going to be filled with the information from
     * the table
     */
    @FXML
    private void fillTextFields() {
        table.setRowFactory(t -> {
            TableRow<CustomerOrder> selectedrow = new TableRow<>();
            selectedrow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!selectedrow.isEmpty())) {
                    CustomerOrder rowData = selectedrow.getItem();

                    orderIdTf.setText(String.valueOf(selectedrow.getItem().getCustomerOrderId()));
                    orderIdTf.setEditable(false);

                    mailTf.setText(String.valueOf(selectedrow.getItem().getEmail()));
                    mailTf.setEditable(false);

                    amountTf.setText(String.valueOf(selectedrow.getItem().getAmount()));
                    amountTf.setEditable(false);

                    deliveryDateTf.setText(String.valueOf(selectedrow.getItem().getDeliveryDate()));
                    deliveryDateTf.setEditable(false);

                    destinationAddressTf.setText(String.valueOf(selectedrow.getItem().getDestinationAddress()));
                    destinationAddressTf.setEditable(false);

                    destinationPostcodeTf.setText(String.valueOf(selectedrow.getItem().getPostcode()));
                    destinationPostcodeTf.setEditable(false);

                    pickupLocationTf.setText(String.valueOf(selectedrow.getItem().getPickUpAddress()));
                    pickupLocationTf.setEditable(false);

                    isHazardousCbQuotation.setSelected(selectedrow.getItem().isHazardous());
                    //isHazardousCalculator.setSelected(selectedrow.getItem().isHazardous());

                    System.out.println(rowData);
                }
            });
            return selectedrow;
        });
    }

    /**
     * @param actionEvent Getting the proposed price from the input field {@link #totalPriceTf}
     *                    Update total Price of currently selected CustomerOrder
     *                    Delegating to the Dao to update the database
     */
    @FXML
    public void handleSendButton(ActionEvent actionEvent) {
        double proposedPrice = Double.parseDouble(totalPriceTf.getText().trim());
        CustomerOrder customerOrder = table.getSelectionModel().getSelectedItem();
        customerOrder.setProposedPrice(proposedPrice);
        accountantLogic.approved(customerOrder);
    }

    @FXML
    public void handleRejectButton(ActionEvent actionEvent) {
        CustomerOrder customerOrder = table.getSelectionModel().getSelectedItem();
        accountantLogic.reject(customerOrder);
    }


    /**
     * @param e
     * as soon as the calculate button is pressed the system calcualtes a price
     */
    @FXML
    protected void handleButtonAction(ActionEvent e) {
        String kms = kilometersCalculatorTf.getText();
        String pricePerKilometers = pricePerKilometerCalculatorTf.getText();
        boolean hazardousSelected = isHazardousCalculator.isSelected();

        double kmsD = Double.parseDouble(kms);
        double pricePerKmsD = Double.parseDouble(pricePerKilometers);

        Calculator calculator = new Calculator(kmsD, hazardousSelected, pricePerKmsD);
        //AccountantLogic accountantLogic = new AccountantLogic();
        priceTextAreaCalculator.setText("" + accountantLogic.calculatePrice(calculator));
    }


    /**
     * @param e
     * clears the textfields of the calcualtor on  button click
     */
    //clear button
    @FXML
    private void handleClearButtonEvent(ActionEvent e) {
        kilometersCalculatorTf.clear();
        kilometersCalculatorTf.setText("");
        pricePerKilometerCalculatorTf.clear();
        pricePerKilometerCalculatorTf.setText("");
        priceTextAreaCalculator.clear();
        isHazardousCalculator.setSelected(false);
    }
}
