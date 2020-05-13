package Fx;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainViewController {

    private MainFx mainFx;

    @FXML
    private void goBack() throws IOException { // implementation of back button
        mainFx.showMainItems();
    }

    @FXML
    private void addButton() throws IOException { // implementation of add Employee button
        mainFx.showAddStage();

    }

    @FXML
    private void goTest() throws IOException { // implementation of back button
        mainFx.showTestStage();


    }
}
