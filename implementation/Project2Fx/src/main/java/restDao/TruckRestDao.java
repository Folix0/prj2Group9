package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomerOrder;
import entities.Planner;
import entities.Truck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class TruckRestDao implements DAOlite<Truck>{
    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/truck";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/truck/";


    private static JSONObject getObjectFromWebsite(final String url) throws IOException {
        final InputStream inputStream = new URL(url).openStream();

        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            final String rawJasonText = read(bufferedReader);
            final JSONObject jsonObject = new JSONObject(rawJasonText);
            System.out.println(jsonObject);

            return jsonObject;

        } finally {
            inputStream.close();

        }

    }

    private static String read(final Reader reader) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        int counter;

        while ((counter = reader.read()) != -1) {
            stringBuilder.append((char) counter);
        }
        return stringBuilder.toString();
    }

    private static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @POST
    @Path("truck")
    @Override
    public void save(Truck truck)  {
        var values = new HashMap<String, String>() {{

            put("id", "" + truck.getId());
            put("truckLicensePlate", "" + truck.getTruckLicensePlate());
            put("truckPickupLocation", "" + truck.getTruckPickupLocation());
            put("truckWeight", "" + truck.getTruckWeight());
            put("mileage", "" + truck.getMileage());
            put("isAvailable", "" + truck.isAvailable());
            put("maintenanceCheckdate", "" + truck.getMaintenanceCheckdate());
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
                .uri(URI.create("http://localhost:8080/webapi/myresource/truck"))
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
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(GET_URL + "" + id))
                .build();

        HttpResponse<String> response = null;
        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //altered variable name
            JSONObject jsonObject = new JSONObject(response.body());


            int truckId = jsonObject.getInt("id");
            String licencePlate = jsonObject.getString("truckLicensePlate");
            String pickUp = jsonObject.getString("truckPickupLocation");
            double weight = jsonObject.getDouble("truckWeight");
            int mileage = jsonObject.getInt("mileage");
            boolean available = jsonObject.getBoolean("isAvailable");
            LocalDate maintenanceCheck = LocalDate.parse(jsonObject.getString("maintenanceCheckdate"));


            Truck truck = new Truck(truckId,licencePlate,available,weight,mileage,
                    pickUp,maintenanceCheck);


            return Optional.of(truck);


            //altered variable name
        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }


    }



    @Override
    public Optional<Collection<Truck>> getAll() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(GET_ALL_URL))
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
                boolean available = jsonObject.getBoolean("available");
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
    public void update(Truck truck) {

    }

    @Override
    public void delete(int id) {

    }
}
