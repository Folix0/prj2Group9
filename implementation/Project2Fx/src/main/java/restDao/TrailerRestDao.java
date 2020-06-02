package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Trailer;
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

public class TrailerRestDao implements DAOlite<Trailer>{

    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/trailer";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/trailer/";

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
    @Path("trailer")
    @Override
    public void save(Trailer trailer) {
        var values = new HashMap<String, String>() {{

            put("id", "" + trailer.getId());
            put("trailerLicencePlate", "" + trailer.getTrailerLicencePlate());
            put("trailerPickupLocation", "" + trailer.getTrailerPickupLocation());
            put("capacity", "" + trailer.getCapacity());
            put("trailerWeight", "" + trailer.getTrailerWeight());
            put("isCleaned", "" + trailer.isCleaned());
            put("isAvailable", "" + trailer.isAvailable());
            put("isHazardous", "" + trailer.isHazardous());
            put("maintenanceCheckdate", "" + trailer.getMaintenanceCheckdate());
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
                .uri(URI.create("http://localhost:8080/webapi/myresource/trailer"))
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
    public Optional<Trailer> get(int id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(GET_URL + ""+id))
                .build();

        HttpResponse<String> response = null;
        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //altered variable name
            JSONObject jsonObject = new JSONObject(response.body());



            int trailerId = jsonObject.getInt("id");
            String licencePlate = jsonObject.getString("trailerLicencePlate");
            String trailerPickUp = jsonObject.getString("trailerPickupLocation");
            double capacity = jsonObject.getDouble("capacity");
            double trailerWeight = jsonObject.getDouble("trailerWeight");
            boolean isCleaned = jsonObject.getBoolean("isCleaned");
            boolean isAvailable = jsonObject.getBoolean("isAvailable");
            boolean isHazardous = jsonObject.getBoolean("isHazardous");
            LocalDate maintenanceCheck = LocalDate.parse(jsonObject.getString("maintenanceCheckdate"));

            Trailer trailer = new Trailer(trailerId, licencePlate, isCleaned, isAvailable, isHazardous,capacity, trailerPickUp, maintenanceCheck,trailerWeight);




            return Optional.of(trailer);


            //altered variable name
        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }


    }

    @Override
    public Optional<Collection<Trailer>> getAll() {
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
            ObservableList<Trailer> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                System.out.println(jsonObject);
                int trailerId = jsonObject.getInt("id");
                String licencePlate = jsonObject.getString("trailerLicencePlate");
                String trailerPickUp = jsonObject.getString("trailerPickupLocation");
                double capacity = jsonObject.getDouble("capacity");
                double trailerWeight = jsonObject.getDouble("trailerWeight");
                boolean isCleaned = jsonObject.getBoolean("cleaned");
                boolean isAvailable = jsonObject.getBoolean("available");
                boolean isHazardous = jsonObject.getBoolean("hazardous");
                LocalDate maintenanceCheck = LocalDate.parse(jsonObject.getString("maintenanceCheckdate"));



                Trailer trailer = new Trailer(trailerId, licencePlate, isCleaned, isAvailable, isHazardous,capacity, trailerPickUp, maintenanceCheck,trailerWeight);

                list.add(trailer);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Trailer update(Trailer trailer) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}


