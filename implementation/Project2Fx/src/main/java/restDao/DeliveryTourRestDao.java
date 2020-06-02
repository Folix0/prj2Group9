package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.DeliveryTour;
import entities.Planner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class DeliveryTourRestDao implements DAOlite<DeliveryTour>{
    private static final String API_URL = "http://localhost:8080/webapi/myresource/deliverytour";

    @Override
    public void save(DeliveryTour deliveryTour) throws IOException {
        var values = new HashMap<String, String>() {{

            put("id", "" + deliveryTour.getId());
            put("startofdeliverydate", "" + deliveryTour.getStartOfDeliveryDate());
            put("finishofdeliverydate", "" + deliveryTour.getFinishOfDeliveryDate());
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
    public Optional<DeliveryTour> get(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<DeliveryTour>> getAll() {
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
            ObservableList<DeliveryTour> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                int id = jsonObject.getInt("id");
                LocalDate start = LocalDate.parse(jsonObject.getString("startofdeliverydate"));
                LocalDate finish = LocalDate.parse(jsonObject.getString("finishofdeliverydate"));




                DeliveryTour deliveryTour = new DeliveryTour(id, start, finish);

                list.add(deliveryTour);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public DeliveryTour update(DeliveryTour deliveryTour) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

