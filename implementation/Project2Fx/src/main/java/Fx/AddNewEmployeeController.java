package Fx;

import entities.CustomerOrder;
import entities.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import restDao.DriverRestDao;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {


    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public DatePicker birthYear;
    @FXML
    public TextField email;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField address;
    @FXML
    public CheckBox HazardousLicense;
    @FXML
    private Button add;


    @FXML
    protected void Sending(ActionEvent e) {

        String fName = firstName.getText().trim();
        String lName = lastName.getText().trim();
        LocalDate birthDate = birthYear.getValue();
        String email = this.email.getText().trim();
        String phoneNumber = this.phoneNumber.getText().trim();
        String address = this.address.getText().trim();
        Boolean hazardous = this.HazardousLicense.isSelected();


        Driver driver = new Driver(true, hazardous, fName, lName, birthDate, email, phoneNumber, address);

        DriverRestDao driverRestDao = new DriverRestDao();

        driverRestDao.save(driver);

        System.out.println(driver);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add.setOnAction(this::Sending);
        ObservableList<CustomerOrder> testController5 = FXCollections.observableArrayList();


    }
}
