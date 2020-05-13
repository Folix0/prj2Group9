package Fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddNewEmployeeController {

    ObservableList<String> employeeDepartment = FXCollections.observableArrayList("Driver","Planner", "Accountant");

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

    /*@FXML
    private TableView employeeTable;
    @FXML
    private TableColumn<UserTestClass, Integer> empIdColumn;
    @FXML
    private TableColumn<UserTestClass, String>  empFirstNameColumn;
    @FXML
    private TableColumn<UserTestClass, String> empLastNameColumn;
    @FXML
    private TableColumn<UserTestClass, String> empEmailColumn;*/

    //Choice box

    @FXML
    private ChoiceBox department;

    @FXML
    private void initialize(){
        department.setValue("Driver");
        department.setItems(employeeDepartment);

    }
}
