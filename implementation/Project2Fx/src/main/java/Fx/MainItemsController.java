package Fx;

import javafx.fxml.FXML;

import java.io.IOException;


public class MainItemsController {

    private MainFx mainFx;

    @FXML
    public void goDriver() throws IOException { // Button reacts from main items to individual pages
        mainFx.showDriverScene();

    }

    @FXML
    public void goAccountant() throws IOException {
        mainFx.showAccountantScene();

    }

    @FXML
    public void goPlanner() throws IOException {
        mainFx.showPlannerScene();

    }

}
