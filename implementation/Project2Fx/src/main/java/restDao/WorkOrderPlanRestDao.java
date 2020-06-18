package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.CustomerOrder;
import entities.WorkOrderPlan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class WorkOrderPlanRestDao implements DAOlite<WorkOrderPlan> {

    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/workorderplan";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/workorderplan/";


    private Optional<String> toJson(WorkOrderPlan workOrderPlan) {
        var values = new HashMap<String, String>() {{
            put("orderId", "" + workOrderPlan.getOrderId());
            put("driverId", "" + workOrderPlan.getDriverId());
            put("trailerLicencePlate", "" + workOrderPlan.getTrailerLicencePlate());
            put("truckLicencePlate", "" + workOrderPlan.getTruckLicencePlate());
            put("trailerPickupLocation", "" + workOrderPlan.getTrailerPickupLocation());
            put("loadAmount", "" + workOrderPlan.getLoadAmount());
            put("hazardousLiquid", "" + workOrderPlan.isHazardousLiquid());
            put("trailerCleaned", "" + workOrderPlan.isTrailerCleaned());
            put("deliveryAddress", "" + workOrderPlan.getDeliveryAddress());
            put("deliveryPostcode", "" + workOrderPlan.getDeliveryPostcode());
            put("deliveryDate", "" + workOrderPlan.getDeliveryDate());
        }};

       /* for (String x : values.keySet()) {
            System.out.println(x);
            System.out.println(values.get(x));

        }*/

        var objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(values);
        }
        catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        return Optional.ofNullable(requestBody);
    }

    @Override
    public void save(WorkOrderPlan workOrderPlan){
        Optional<String> optionalRequestBody = toJson(workOrderPlan);
        if (!optionalRequestBody.isPresent()) {
            System.out.println("workorderplan could not be converted to json");
            return;
        } else {
            String requestBody = optionalRequestBody.get();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create("http://localhost:8080/webapi/myresource/workorderplan"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Optional<WorkOrderPlan> get(int id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(GET_URL + "" + id))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());

            int workId = jsonObject.getInt("id");
            int orderId = jsonObject.getInt("orderid");
            int driverId = jsonObject.getInt("driverid");
            String trailerLicencePlate = jsonObject.getString("trailerlicenceplate");
            String truckLicencePlate = jsonObject.getString("trucklicenceplate");
            String trailerPickupLocation = jsonObject.getString("trailerpickuplocation");
            double amount = jsonObject.getDouble("loadamount");
            boolean hazardousLiquid = jsonObject.getBoolean("hazardousliquid");
            boolean trailerCleaned = jsonObject.getBoolean("trailercleaned");
            String deliveryAddress = jsonObject.getString("deliveryaddress");
            String deliveryPostcode = jsonObject.getString("deliverypostcode");
            LocalDate deliveryDate = LocalDate.parse(jsonObject.getString("deliveryDate"));

            WorkOrderPlan workOrderPlan = new WorkOrderPlan(workId, orderId, driverId,
                    trailerLicencePlate, truckLicencePlate, trailerPickupLocation, amount, hazardousLiquid, trailerCleaned,
                    deliveryAddress, deliveryPostcode, deliveryDate);

            return Optional.of(workOrderPlan);
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    @Override
    public Optional<Collection<WorkOrderPlan>> getAll() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(GET_ALL_URL))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONArray jsonArray = new JSONArray(response.body());

            GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter( LocalDate.class, new ConvertLocalDate());

            Gson gson = gsonBuilder.create();
            Type type = TypeToken.getParameterized(ArrayList.class, WorkOrderPlan.class).getType();
            List<WorkOrderPlan> json = gson.fromJson(response.body(), type);
            System.out.println("-----------------------" +json);
            ObservableList<WorkOrderPlan> list = FXCollections.observableArrayList(json);

            return Optional.of(list);
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * @param workOrderPlan to be updated in the database
     *                      Convert java object into json object
     *                      Sends json object to the rest api
     */

    @Override
    public void update(WorkOrderPlan workOrderPlan) {
        Optional<String> optionalRequestBody = toJson(workOrderPlan);
        if (!optionalRequestBody.isPresent()) {
            System.out.println("CustomerOrder could not be converted to json");
            return;
        } else {
            String requestBody = optionalRequestBody.get();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create("http://localhost:8080/webapi/myresource/workorderplan"))
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void delete(int id) {

    }
}
