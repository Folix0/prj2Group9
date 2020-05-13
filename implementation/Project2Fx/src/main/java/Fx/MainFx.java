package Fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainFx extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("We Truck that");
        Image image = new Image("/icons/Logo_prj2_png.png");
        primaryStage.getIcons().add(image);
        showMainView();
        showMainItems();
    }

    public static void showMainView() throws IOException { //show main view of all the scenes at the bottom
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFx.class.getResource("/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showMainItems() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFx.class.getResource("/MainItems.fxml"));
        BorderPane mainItems = loader.load();
        Scene s = new Scene(mainItems);
        s.getStylesheets().add("/Stylesheet.css");
        mainLayout.setCenter(mainItems);
    }

    public static void showDriverScene() throws IOException { //show individual view of scene driver
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFx.class.getResource("/OwnerDriver.fxml"));
        BorderPane driver = loader.load();
        mainLayout.setCenter(driver);
    }

    public static void showAccountantScene() throws IOException { //show individual view of scene accountant
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFx.class.getResource("/OwnerAccountant.fxml"));
        BorderPane accountant = loader.load();
        mainLayout.setCenter(accountant);
    }

    public static void showPlannerScene() throws IOException { //show individual view of scene planner
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFx.class.getResource("/OwnerPlanner.fxml"));
        BorderPane planner = loader.load();
        mainLayout.setCenter(planner);
    }

    public static void showAddStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFx.class.getResource("/AddNewEmployee.fxml"));
        BorderPane addNewEmployee = loader.load();
        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Add new Employee");
        Image image = new Image("/icons/Logo_prj2_png.png");
        addDialogStage.getIcons().add(image);
        addDialogStage.initModality(Modality.WINDOW_MODAL);// background selection not available
        addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(addNewEmployee);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();
    }

    public static void showTestStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFx.class.getResource("/UserTestFxml.fxml"));
        BorderPane userTestFxml = loader.load();
        Scene scene = new Scene(userTestFxml);
        Stage addTestStage = new Stage();
        addTestStage.setTitle("Test");
        addTestStage.initModality(Modality.WINDOW_MODAL);// background selection not available
        addTestStage.initOwner(primaryStage);
        addTestStage.setScene(scene);
        addTestStage.showAndWait();
    }
}


