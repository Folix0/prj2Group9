package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Planner;
import entities.Truck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class TruckRestDao implements DAOlite<Truck>{
    private static final String API_URL = "http://localhost:8080/webapi/myresource/truck";

    @POST
    @Path("customerorder")
    @Override
    public void save(Truck truck) throws IOException {
        var values = new HashMap<String, String>() {{

            put("id", "" + truck.getId());
            put("trucklicenceplate", "" + truck.getTruckLicensePlate());
            put("truckpickuplocation", "" + truck.getTruckPickupLocation());
            put("truckweight", "" + truck.getTruckWeight());
            put("mileage", "" + truck.getMileage());
            put("isavailable", "" + truck.isAvailable());
            put("maintenancecheckdate", "" + truck.getMaintenanceCheckdate());
        }};

        for (String x : values.keySet()) {
            System.out.println(x);
            System.out.println(values.get(x));

        }

        var objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(values);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        System.out.println(response.body());
    }

    @Override
    public Optional<Truck> get(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<Truck>> getAll() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = null;
        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONArray jsonArray = new JSONArray(response.body());
            ObservableList<Truck> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                int id = jsonObject.getInt("id");
                String licencePlate = jsonObject.getString("truckLicensePlate");
                String pickUp = jsonObject.getString("truckPickupLocation");
                double weight = jsonObject.getDouble("truckWeight");
                int mileage = jsonObject.getInt("mileage");
                boolean available = jsonObject.getBoolean("isAvailable");
                LocalDate maintenanceCheck = LocalDate.parse(jsonObject.getString("maintenanceCheckdate"));




                Truck truck = new Truck(id, licencePlate, available, weight, mileage, pickUp, maintenanceCheck);

                list.add(truck);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Truck update(Truck truck) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
