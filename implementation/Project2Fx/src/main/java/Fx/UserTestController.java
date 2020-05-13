package Fx;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;




public class UserTestController /*implements Initializable*/ {

    @FXML
    private Button addUser;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextArea resultArea;

    @FXML
    private Button search;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button searchAll;

    @FXML
    private TableView tableView;/*

    @FXML
    private TableColumn<UserTestClass, SimpleStringProperty> fName;

    @FXML
    private TableColumn<UserTestClass, SimpleStringProperty> lName;

    @FXML
    private TableColumn<UserTestClass, SimpleStringProperty> eMail;

   /* @FXML
    public void ObservableList<UserTestDao> getTestController() {

        ObservableList<UserTestDao> TestController = FXCollections.observableArrayList();

        for (UserTestDao u : USER_DAO.getAll()) {
            TestController.add(u);
        }
        tableView.getColumns()
    }*/

  /*  @FXML
    public static ObservableList<UserTestClass> getTestController() {
        List<UserTestClass> theList = new ArrayList(USER_DAO.getAll());
        ObservableList<UserTestClass> TestController = FXCollections.observableArrayList();

        for (UserTestClass o : theList) {
            TestController.add(o);


        }
        return TestController;
    }

    @FXML
    public void addUser(ActionEvent actionEvent) {
        USER_DAO.save(new UserTestClass(firstName.getText(), lastName.getText(), email.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName.setCellValueFactory(new PropertyValueFactory<UserTestClass, SimpleStringProperty>("firstName"));
        lName.setCellValueFactory(new PropertyValueFactory<UserTestClass, SimpleStringProperty>("lastName"));
        eMail.setCellValueFactory(new PropertyValueFactory<UserTestClass, SimpleStringProperty>("email"));
        tableView.setItems(getTestController());
    }

    @FXML
    public void refresh() {
        List<UserTestClass> theList = new ArrayList(USER_DAO.getAll());
        ObservableList<UserTestClass> TestController = FXCollections.observableArrayList();
        tableView.getItems().clear();
        for (UserTestClass o : theList) {
            TestController.add(o);
        }
        tableView.setItems(TestController);
    }

    @FXML
    public void delete() {
        List<UserTestClass> theList = new ArrayList(USER_DAO.getAll());
        ObservableList<UserTestClass> TestController = FXCollections.observableArrayList();

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        tableView.setEditable(true);
        tableView.getItems().remove(selectedIndex);
        USER_DAO.delete(theList.get(selectedIndex));
    }*/
}

