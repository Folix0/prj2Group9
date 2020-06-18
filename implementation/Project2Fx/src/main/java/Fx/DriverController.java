package Fx;

import entities.WorkOrderPlan;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import restDao.WorkOrderPlanRestDao;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class DriverController implements Initializable {


    ObservableList<WorkOrderPlan> pickupInfo = FXCollections.observableArrayList();
    ObservableList<WorkOrderPlan> deliveryDet = FXCollections.observableArrayList();

    //Pickup Information
    @FXML
    private TableView<WorkOrderPlan> PickupInformation;
    @FXML
    private TableColumn<WorkOrderPlan, String> TruckLicenceD1;
    @FXML
    private TableColumn<WorkOrderPlan, String> TrailerLicenceD1;
    @FXML
    private TableColumn<WorkOrderPlan, String> TrailerPickupD1;
    @FXML
    private TableColumn<WorkOrderPlan, Double> LoadAmountD1;
    @FXML
    private TableColumn<WorkOrderPlan, Boolean> HazardousD;
    @FXML
    private TableColumn<WorkOrderPlan, Boolean> trailerCleaned;

    //Delivery Details
    @FXML
    private TableView<WorkOrderPlan> DeliveryDetails;
    @FXML
    private TableColumn<WorkOrderPlan, String> deliveryAddressD;
    @FXML
    private TableColumn<WorkOrderPlan, String> DeliveryPostcodeD;
    @FXML
    private TableColumn<WorkOrderPlan, LocalDate> DeliveryDateD;

    //Timer
    @FXML
    private AnchorPane timerPane;

    @FXML
    private Text Hours;

    @FXML
    private Text Minutes;

    @FXML
    private Text Seconds;

    @FXML
    private Button Cancel;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private ComboBox<Integer> inputH;

    @FXML
    private ComboBox<Integer> inputM;

    @FXML
    private ComboBox<Integer> inputS;

    @FXML
    private Button Start;

    Map<Integer,String>numberMap;
    Integer currSeconds;
    Thread thread;


    //countdown/ timer

    @FXML
    void startButton(ActionEvent event) {
        currSeconds = hmsToSeconds(inputH.getValue(),inputM.getValue(),inputS.getValue());
        inputH.setValue(0);
        inputM.setValue(0);
        inputS.setValue(0);
        scrollUp();
    }

    void startCountdown(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){

                            setOutput();
                        Thread.sleep(1000);
                        if(currSeconds == 0){
                            System.out.println("Finished");
                            scrollDown();
                            thread.stop();
                        }
                        currSeconds -= 1;
                    }
                }catch (Exception e){

                }
            }
        });
        thread.start();
    }

    void setOutput(){
        LinkedList<Integer>currHms = secondsToHms(currSeconds);
        Hours.setText(numberMap.get(currHms.get(0)));
        Minutes.setText(numberMap.get(currHms.get(1)));
        Seconds.setText(numberMap.get(currHms.get(2)));
    }

    @SuppressWarnings("deprecation")
    @FXML
    void cancelButton(ActionEvent event) {
        thread.stop();
        scrollDown();
    }

    Integer hmsToSeconds(Integer h, Integer m, Integer s){
        Integer hToSeconds = h *3600;
        Integer mToSeconds = m *60;
        Integer total = hToSeconds + mToSeconds +s;
        return total;
    }

    LinkedList<Integer> secondsToHms(Integer currSecond){
        Integer hours = currSecond/ 3600;
        currSecond = currSecond % 3600;
        Integer minutes = currSecond / 60;
        currSecond = currSecond % 60;
        Integer seconds = currSecond;
        LinkedList<Integer> answer = new LinkedList<>();
        answer.add(hours);
        answer.add(minutes);
        answer.add(seconds);
        return answer;
    }

    void scrollUp(){
        TranslateTransition tr1 = new TranslateTransition();
        tr1.setDuration(Duration.millis(100));
        tr1.setToX(0);
        tr1.setToY(-200);
        tr1.setNode(menuPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(timerPane);
        ParallelTransition pt = new ParallelTransition(tr1,tr2);
        pt.setOnFinished(e -> {
            try {
                System.out.println("Start Countdown...");
                startCountdown();
            }catch (Exception e2){

            }
        });
        pt.play();
    }

    void scrollDown(){
        TranslateTransition tr1 = new TranslateTransition();
        tr1.setDuration(Duration.millis(100));
        tr1.setToX(0);
        tr1.setToY(200);
        tr1.setNode(timerPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(-200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(menuPane);
        ParallelTransition pt = new ParallelTransition(tr1,tr2);
        pt.play();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPickupInformationTable();
        setDeliveryDetailsTable();
        ObservableList<Integer> hourList = FXCollections.observableArrayList();
        ObservableList<Integer> minutesSecondsList = FXCollections.observableArrayList();
        for (int i = 0; i <= 60; i++) {
            if (0 <= i && i <= 24) {
                hourList.add(new Integer(i));
            }
            minutesSecondsList.add(new Integer(i));
        }
        inputH.setItems(hourList);
        inputH.setValue(0);
        inputM.setItems(minutesSecondsList);
        inputM.setValue(0);
        inputS.setItems(minutesSecondsList);
        inputS.setValue(0);

        numberMap = new TreeMap<Integer,String>();
        for(Integer i = 0; i<= 60; i++){
            if(0 <=i && i <= 9){
                numberMap.put(i,"0" + i.toString());
            }else{
                numberMap.put(i, i.toString());
            }
        }
    }


    /**
     * fill the table with information
     * create insatance of work order plan dao
     * invoke getall method
     * set the items in the table cell
     */
    @FXML
    private void setPickupInformationTable() {
        TruckLicenceD1.setCellValueFactory(new PropertyValueFactory<>("truckLicencePlate"));
        TrailerLicenceD1.setCellValueFactory(new PropertyValueFactory<>("trailerLicencePlate"));
        TrailerPickupD1.setCellValueFactory(new PropertyValueFactory<>("trailerPickupLocation"));
        LoadAmountD1.setCellValueFactory(new PropertyValueFactory<>("loadAmount"));
        HazardousD.setCellValueFactory(new PropertyValueFactory<>("hazardousLiquid"));
        trailerCleaned.setCellValueFactory(new PropertyValueFactory<>("trailerCleaned"));

        WorkOrderPlanRestDao workOrderPlanRestDao = new WorkOrderPlanRestDao();
        Optional<Collection<WorkOrderPlan>> maybeList1 = workOrderPlanRestDao.getAll();

        if (maybeList1.isPresent()) {
            PickupInformation.setItems((ObservableList<WorkOrderPlan>) maybeList1.get());
            System.out.println(maybeList1);

        } else {
            System.out.println("Error could not retrieve data from backend (Driver)");
        }
        pickupInfo.addAll(PickupInformation.getItems());
    }


    /**
     * set the table with information
     * call get all to retrieve the information 
     */
    @FXML
    private void setDeliveryDetailsTable() {
        deliveryAddressD.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        DeliveryPostcodeD.setCellValueFactory(new PropertyValueFactory<>("deliveryPostcode"));
        DeliveryDateD.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));

        WorkOrderPlanRestDao workOrderPlanRestDao = new WorkOrderPlanRestDao();
        Optional<Collection<WorkOrderPlan>> maybeList1 = workOrderPlanRestDao.getAll();

        if (maybeList1.isPresent()) {
            DeliveryDetails.setItems((ObservableList<WorkOrderPlan>) maybeList1.get());
            System.out.println(maybeList1);

        } else {
            System.out.println("Error could not retrieve data from backend (Driver)");
        }
        deliveryDet.addAll(DeliveryDetails.getItems());
    }
}
