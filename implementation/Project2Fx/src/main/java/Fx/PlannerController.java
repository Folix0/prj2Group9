package Fx;



import businessLogic.PlannerLogic;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import restDao.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class PlannerController implements Initializable {


    //Driver
    @FXML
    private TableView<Driver> driverTable;
    @FXML
    private TableColumn<Driver, Integer> id;
    @FXML
    private TableColumn<Driver, String> Email;
    @FXML
    private TableColumn<Driver, String> PhoneNumber;
    @FXML
    private TableColumn<Driver, Boolean> AvailableD;
    @FXML
    private TableColumn<Driver, Boolean> HazardousLicenseD;


    //Trailer
    @FXML
    private TableView<Trailer> trailer;
    @FXML
    private TableColumn<Trailer, String> LicencePlate;
    @FXML
    private TableColumn<Trailer, Boolean> Available;
    @FXML
    private TableColumn<Trailer, Boolean> Hazardous;
    @FXML
    private TableColumn<Trailer, Boolean> Cleaned;
    @FXML
    private TableColumn<Trailer, Double> Weight;
    @FXML
    private TableColumn<Trailer, Double> Capacity;
    @FXML
    private TableColumn<Trailer, String> PickupLocation;
    @FXML
    private TableColumn<Trailer, LocalDate> Maintenance;


    //Truck
    @FXML
    private TableView<Truck> Truck;
    @FXML
    private TableColumn<Truck, String> LicencePlateT;
    @FXML
    private TableColumn<Truck, Boolean> AvailableT;
    @FXML
    private TableColumn<Truck, Double> WeightT;
    @FXML
    private TableColumn<Truck, Integer> Mileage;
    @FXML
    private TableColumn<Truck, String> PickupLocationT;
    @FXML
    private TableColumn<Truck, LocalDate> MaintenanceT;


    //accepted quotation tab
    @FXML
    private TableView<CustomerOrder> plannerAcceptedQuotationTable;
    @FXML
    private TableColumn<CustomerOrder, Integer> orderIdPlanner;
    @FXML
    private TableColumn<CustomerOrder, String> emailP;
    @FXML
    private TableColumn<CustomerOrder, Double> amountP;
    @FXML
    private TableColumn<CustomerOrder, LocalDate> deliveryDateP;
    @FXML
    private TableColumn<CustomerOrder, String> destinationAddressP;
    @FXML
    private TableColumn<CustomerOrder, String> destinationPostcodeP;
    @FXML
    private TableColumn<CustomerOrder, String> pickupLocationP;
    @FXML
    private TableColumn<CustomerOrder, Boolean> hazardousP;
    @FXML
    private TableColumn<CustomerOrder, String> statusQuotationP;


    //work order plan tab
    @FXML
    private TextField orderIdWorkOrder;
    @FXML
    private TextField driverIdWorkOrder;
    @FXML
    private TextField trailerLicensePlateWorkOrder;
    @FXML
    private TextField truckLicensePlateWorkOrder;
    @FXML
    private TextField trailerPickupLocationWorkOrder;
    @FXML
    private TextField loadAmountWorkOrder;
    @FXML
    private CheckBox hazardousWorkOrder;
    @FXML
    private CheckBox isCleanedWorkOrder;
    @FXML
    private TextField deliveryAddressWorkOrder;
    @FXML
    private TextField deliveryPostcodeWorkOrder;
    @FXML
    private TextField deliveryDateWorkOrder;
    @FXML
    private Button createBtnWorkOrder;


    //truck/trailer panel planner search
    @FXML
    private TextField searchTfTruck;
    @ FXML
    private TextField Search;
    @FXML
    private TextField textfieldTrailer;

    //for search functions
    ObservableList<Truck> trucks = FXCollections.observableArrayList();
    ObservableList<Driver> drivers = FXCollections.observableArrayList();
    ObservableList<Trailer> trailers = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataDriverTable();
        setDataTruckTable();
        setDataTrailerTable();
        setDataAcceptedQuotationTable();
        selectTableRowDriverTable();
        selectTableRowTrailerTable();
        selectTruckTableRow();
        selectAcceptedQuotationRow();
        createBtnWorkOrder.setOnAction(this::CreateWorkOrderSend);
    }


    /**
     * retrieve data and set them in the table
     */
    @FXML
    private void setDataAcceptedQuotationTable() {
        orderIdPlanner.setCellValueFactory(new PropertyValueFactory<>("customerOrderId"));
        emailP.setCellValueFactory(new PropertyValueFactory<>("email"));
        amountP.setCellValueFactory(new PropertyValueFactory<>("amount"));
        deliveryDateP.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        destinationAddressP.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
        destinationPostcodeP.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        pickupLocationP.setCellValueFactory(new PropertyValueFactory<>("pickUpAddress"));
        hazardousP.setCellValueFactory(new PropertyValueFactory<>("hazardous"));
        statusQuotationP.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        CustomerOrderRestDao customerOrderRestDao = new CustomerOrderRestDao();
        Optional<Collection<CustomerOrder>> maybelist = customerOrderRestDao.getAll();
        ObservableList<CustomerOrder> accepted = FXCollections.observableArrayList();

        //List<CustomerOrder> orders = new ArrayList<>();
        //PlannerLogic.defineOrderStatus(accepted, maybelist);
        //plannerAcceptedQuotationTable.setItems(accepted);

        for(CustomerOrder  c: maybelist.get()){
            if(c.getOrderStatus().equals("Accepted")){
                accepted.add(c);
            }
        }
        if (!accepted.isEmpty()) {
            plannerAcceptedQuotationTable.setItems(accepted);
            System.out.println(accepted);
        } else {
            System.out.println("Error could not retrieve data from backend (CustomerOrder)");
        }
    }

    @FXML
    private void setDataDriverTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        AvailableD.setCellValueFactory(new PropertyValueFactory<>("available"));
        HazardousLicenseD.setCellValueFactory(new PropertyValueFactory<>("hasHazardousLicense"));

        DriverRestDao driverRestDao = new DriverRestDao();
        Optional<Collection<Driver>> maybeList1 = driverRestDao.getAll();

        if (maybeList1.isPresent()) {
            driverTable.setItems((ObservableList<Driver>) maybeList1.get());
            System.out.println(maybeList1);

        } else {
            System.out.println("Error could not retrieve data from backend (Driver)");
        }
        drivers.addAll(driverTable.getItems());
    }

    @FXML
    private void setDataTruckTable() {
        LicencePlateT.setCellValueFactory(new PropertyValueFactory<>("truckLicensePlate"));
        AvailableT.setCellValueFactory(new PropertyValueFactory<>("available"));
        WeightT.setCellValueFactory(new PropertyValueFactory<>("truckWeight"));
        Mileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        PickupLocationT.setCellValueFactory(new PropertyValueFactory<>("truckPickupLocation"));
        MaintenanceT.setCellValueFactory(new PropertyValueFactory<>("maintenanceCheckdate"));

        TruckRestDao truckRestDao = new TruckRestDao();
        Optional<Collection<Truck>> maybeList2 = truckRestDao.getAll();

        if (maybeList2.isPresent()) {
            Truck.setItems((ObservableList<Truck>) maybeList2.get());
            System.out.println(maybeList2);

        } else {
            System.out.println("Error could not retrieve data from backend (Trailer)");
        }

        //retrieve data from table
        //Truck = table
        trucks.addAll(Truck.getItems());
    }


    @FXML
    private void setDataTrailerTable () {
        //attribute from entity/ rest api -> json (attributes from webbrowser)
        LicencePlate.setCellValueFactory(new PropertyValueFactory<>("trailerLicencePlate"));
        Available.setCellValueFactory(new PropertyValueFactory<>("available"));
        Hazardous.setCellValueFactory(new PropertyValueFactory<>("hazardous"));
        Weight.setCellValueFactory(new PropertyValueFactory<>("trailerWeight"));
        Cleaned.setCellValueFactory(new PropertyValueFactory<>("cleaned"));
        Capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        PickupLocation.setCellValueFactory(new PropertyValueFactory<>("trailerPickupLocation"));
        Maintenance.setCellValueFactory(new PropertyValueFactory<>("maintenanceCheckdate"));

        TrailerRestDao trailerRestDao = new TrailerRestDao();
        Optional<Collection<Trailer>> maybeList = trailerRestDao.getAll();

        if (maybeList.isPresent()) {
            trailer.setItems((ObservableList<Trailer>) maybeList.get());
            System.out.println(maybeList);

        } else {
            System.out.println("Error could not retrieve data from backend (Trailer)");
        }
        //trailer = table
        trailers.addAll(trailer.getItems());
    }



    //search functions

    /**
     *
     * enter a search term in the textfield -> display data which match the search term
     */
    @FXML
    private void handleTextfieldTruck() {
        FilteredList<Truck> filterData = new FilteredList<>(trucks, p -> true);
        searchTfTruck.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(truck -> {
               Truck.getItems();
                if (newvalue == null || newvalue.isEmpty()) {
                    //true ->  after deleting search term all results are
                    //displayed
                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (PlannerLogic.filterTruckTable(typedText, truck)) {
                    return true;
                }
                return !true;
            });
            SortedList<Truck> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(Truck.comparatorProperty());
            Truck.setItems(sortedList);
        });
    }

    //search driver
    @FXML
    private void SearchRecordDriver() {
        FilteredList<Driver> filterData = new FilteredList<>(drivers, p -> true);
        Search.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(driver -> {
                driverTable.getItems();
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (PlannerLogic.filterDriverTable(typedText, driver)) {
                    return true;
                }
                return !true;
            });
            SortedList<Driver> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(driverTable.comparatorProperty());
            driverTable.setItems(sortedList);
        });
    }

    //search trailer
    @FXML
    private void handleTfTrailer() {
        FilteredList<Trailer> filterData = new FilteredList<>(trailers, p -> true);
        textfieldTrailer.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(trailer1 -> {
                trailer.getItems();
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (PlannerLogic.filterTrailerTable(typedText, trailer1)) {
                    return true;
                }
                return !true;
            });
            SortedList<Trailer> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(trailer.comparatorProperty());
            trailer.setItems(sortedList);
        });
    }




    //select rows table

    /**
     * if a table row is double clicked it takes values from the table and
     * sets them in the textfields in the work order plan panel
     * textfields are non-editable
     */
        @FXML
        private void selectTableRowDriverTable () {
            driverTable.setRowFactory(t -> {
                TableRow<Driver> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Driver rowData = row.getItem();
                        driverIdWorkOrder.setText(String.valueOf(row.getItem().getId()));
                        driverIdWorkOrder.setEditable(false);
                        System.out.println(rowData);
                    }
                });
                return row;
            });
        }


        /**
         * if row of trailer table is selected  get the items trailer licence plate, trailer pickup loc and isCleaned
         * place it in the textfields in the work order plan tab
         * set the textfield not editable so that nothing can be changed
         */

        @FXML
        private void selectTableRowTrailerTable () {
            trailer.setRowFactory(t -> {
                TableRow<Trailer> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Trailer rowData = row.getItem();

                        trailerLicensePlateWorkOrder.setText(String.valueOf(row.getItem().getTrailerLicencePlate()));
                        trailerLicensePlateWorkOrder.setEditable(false);

                        trailerPickupLocationWorkOrder.setText(row.getItem().getTrailerPickupLocation());
                        trailerPickupLocationWorkOrder.setEditable(false);

                        isCleanedWorkOrder.setSelected(row.getItem().isCleaned());
                        isCleanedWorkOrder.setDisable(true);

                        System.out.println(rowData);
                    }
                });
                return row;
            });
        }

        /**
         * if a row form the truck table is selected get the item truck licence plate and
         * place it in the textfield in the work order plan tab
         * and set the textfield not editable so that nothing can be changed
         */

        //sout necessary after each set item method invocation?
        @FXML
        private void selectTruckTableRow () {
            Truck.setRowFactory(t -> {
                TableRow<Truck> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Truck rowData = row.getItem();
                        truckLicensePlateWorkOrder.setText(String.valueOf(row.getItem().getTruckLicensePlate()));
                        truckLicensePlateWorkOrder.setEditable(false);
                        System.out.println(rowData);
                    }
                });
                return row;
            });
        }

        @FXML
        private void selectAcceptedQuotationRow () {
            plannerAcceptedQuotationTable.setRowFactory(t -> {
                TableRow<CustomerOrder> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        CustomerOrder rowData = row.getItem();
                        orderIdWorkOrder.setText(String.valueOf(row.getItem().getCustomerOrderId()));
                        orderIdWorkOrder.setEditable(false);
                        loadAmountWorkOrder.setText(String.valueOf(row.getItem().getAmount()));
                        loadAmountWorkOrder.setEditable(false);
                        deliveryAddressWorkOrder.setText(String.valueOf(row.getItem().getDestinationAddress()));
                        deliveryAddressWorkOrder.setEditable(false);
                        deliveryPostcodeWorkOrder.setText(String.valueOf(row.getItem().getPostcode()));
                        deliveryPostcodeWorkOrder.setEditable(false);
                        hazardousWorkOrder.setSelected(row.getItem().isHazardous());
                        hazardousWorkOrder.setDisable(true);
                        deliveryDateWorkOrder.setText(String.valueOf(row.getItem().getDeliveryDate()));
                        deliveryDateWorkOrder.setEditable(false);

                        System.out.println(rowData);
                    }
                });
                return row;
            });
        }

    /**
     * @param e
     * button create work order on work order plan panel
     * get the text from the textfields
     * create instance of the rest dao (work order)
     * invoke a mehthod of the rest dao -> save -> entry should be saved in the db
     * delegate further processing to this class
     */
        @FXML
        protected void CreateWorkOrderSend (ActionEvent e) {
            int orderId = Integer.parseInt(orderIdWorkOrder.getText().trim());
            int driverId = Integer.parseInt(driverIdWorkOrder.getText().trim());
            String trailerLicence = trailerLicensePlateWorkOrder.getText().trim();
            String truckLicence = truckLicensePlateWorkOrder.getText().trim();
            String pickupLocation = trailerPickupLocationWorkOrder.getText().trim();
            double amount = Double.parseDouble(loadAmountWorkOrder.getText().trim());
            Boolean hazardous = hazardousWorkOrder.isSelected();
            Boolean cleaned = isCleanedWorkOrder.isSelected();
            LocalDate deliveryDate = LocalDate.parse(deliveryDateWorkOrder.getText().trim());
            String deliveryAddress = deliveryAddressWorkOrder.getText().trim();
            String deliveryPostcode = this.deliveryPostcodeWorkOrder.getText().trim();

            WorkOrderPlan workOrderPlan = new WorkOrderPlan(1, orderId, driverId, trailerLicence, truckLicence,
                    pickupLocation, amount, hazardous, cleaned, deliveryPostcode, deliveryAddress, deliveryDate);

            WorkOrderPlanRestDao workOrderPlanRestDao = new WorkOrderPlanRestDao();
            workOrderPlanRestDao.save(workOrderPlan);
            System.out.println(workOrderPlan);
        }
    }