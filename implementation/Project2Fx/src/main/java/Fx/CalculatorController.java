package Fx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateBtn.setOnAction(this::handleButtonAction);
        clearBtn.setOnAction(this::handleClearButtonEvent);
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
        boolean hazardousSelected= hazardous.isSelected();

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
}
