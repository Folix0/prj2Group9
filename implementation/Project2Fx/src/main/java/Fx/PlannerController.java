package Fx;


import entities.Driver;
import entities.Trailer;
import entities.Truck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import restDao.CustomerOrderRestDao;
import restDao.DriverRestDao;
import restDao.TrailerRestDao;
import restDao.TruckRestDao;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlannerController implements Initializable {

    //Driver
    @FXML
    private TableView driver;
    @FXML
    private TableColumn<Driver, Integer> id;
    @FXML
    private TableColumn<Driver, String> FirstName;
    @FXML
    private TableColumn<Driver, String> LastName;
    @FXML
    private TableColumn<Driver, LocalDate> Birthdate;
    @FXML
    private TableColumn<Driver, String> Email;
    @FXML
    private TableColumn<Driver, String> PhoneNumber;
    @FXML
    private TableColumn<Driver, String> Address;
    @FXML
    private TableColumn<Driver, Boolean> AvailableD;
    @FXML
    private TableColumn<Driver, Boolean> HazardousLicenseD;


    //Trailer
    @FXML
    private TableView trailer;
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
    private TableView Truck;
    @FXML
    private TableColumn<Trailer, String> LicencePlateT;
    @FXML
    private TableColumn<Trailer, Boolean> AvailableT;
    @FXML
    private TableColumn<Trailer, Double> WeightT;
    @FXML
    private TableColumn<Trailer, Integer> Mileage;
    @FXML
    private TableColumn<Trailer, String> PickupLocationT;
    @FXML
    private TableColumn<Trailer, LocalDate> MaintenanceT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Driver
        ObservableList<Driver> testController3 = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        Birthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        AvailableD.setCellValueFactory(new PropertyValueFactory<>("available"));
        HazardousLicenseD.setCellValueFactory(new PropertyValueFactory<>("hasHazardousLicense"));

        DriverRestDao driverRestDao = new DriverRestDao();
        Optional<Collection<Driver>> maybeList1 = driverRestDao.getAll();

        if (maybeList1.isPresent()) {
            driver.setItems((ObservableList<Driver>) maybeList1.get());
            System.out.println(maybeList1);

        } else {
            System.out.println("Error could not retrieve data from backend (Driver)");
        }




        //Trailer
        ObservableList<Trailer> testController2 = FXCollections.observableArrayList();

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


        //Truck
        ObservableList<Truck> testController1 = FXCollections.observableArrayList();

        LicencePlateT.setCellValueFactory(new PropertyValueFactory<>("truckLicensePlate"));
        AvailableT.setCellValueFactory(new PropertyValueFactory<>("available"));
        WeightT.setCellValueFactory(new PropertyValueFactory<>("trailerWeight"));
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

    }
}
