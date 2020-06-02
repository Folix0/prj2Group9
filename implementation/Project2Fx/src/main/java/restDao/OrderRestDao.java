package restDao;
/*
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Customer;
import entities.Order;
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

public class OrderRestDao implements DAOlite<Order>{
    private static final String API_URL = "http://localhost:8080/webapi/myresource/aorder";


    @Override
    public void save(Order e){
        var values = new HashMap<String, String>() {{

            //put("id", "" + e.getId());
            put("amount", "" + e.getAmount());
            put("destinationAddress", "" + e.getDestinationAddress());
            put("postcode", "" + e.getPostcode());
            put("pickUpAddress", "" + e.getPickUpAddress());
            put("deliveryDate", "" + e.getDeliveryDate());
            put("hazardous", "" + e.isHazardous());
            put("proposedPrice", "" + e.getProposedPrice());
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
    public Optional<Order> get(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<Order>> getAll() {
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
            ObservableList<Order> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject a = (JSONObject) o;

                int id = a.getInt("id");
                double orderamount = a.getDouble("amount");
                String destinationAddress = a.getString("destinationAddress");
                String destinationPostcode = a.getString("postcode");
                String pickupAddress = a.getString("pickUpAddress");
                LocalDate deliveryDate = LocalDate.parse(a.getString("deliveryDate"));
                boolean hazardous = a.getBoolean("hazardous");
                double proposedPrice = a.getDouble("proposedPrice");



                 Order order = new Order(id, pickupAddress, destinationAddress, destinationPostcode,orderamount,deliveryDate,hazardous,proposedPrice);

                  list.add(order);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Order update(Order e) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

*/