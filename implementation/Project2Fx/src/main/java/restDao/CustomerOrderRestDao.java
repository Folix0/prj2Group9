package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.CustomerOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

public class CustomerOrderRestDao implements DAOlite<CustomerOrder> {

    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/customerorder";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/customerorder/";


    /**
     * @param customerOrder
     * @return
     * converts a java object in a json object
     */
    private Optional<String> toJson(CustomerOrder customerOrder) {
        var values = new HashMap<String, String>() {{

            put("customerOrderId", "" + customerOrder.getCustomerOrderId());
            put("firstName", "" + customerOrder.getFirstName());
            put("lastName", "" + customerOrder.getLastName());
            put("birthDate", "" + customerOrder.getBirthDate());
            put("email", "" + customerOrder.getEmail());
            put("phoneNumber", "" + customerOrder.getPhoneNumber());
            put("address", "" + customerOrder.getAddress());
            put("amount", "" + customerOrder.getAmount());
            put("destinationAddress", "" + customerOrder.getDestinationAddress());
            put("postcode", "" + customerOrder.getPostcode());
            put("pickUpAddress", "" + customerOrder.getPickUpAddress());
            put("deliveryDate", "" + customerOrder.getDeliveryDate());
            put("hazardous", "" + customerOrder.isHazardous());
            put("proposedPrice", "" + customerOrder.getProposedPrice());
            put("orderStatus", "" + customerOrder.getOrderStatus());
        }};

       /* for (String x : values.keySet()) {
            System.out.println(x);
            System.out.println(values.get(x));

        }*/

        var objectMapper = new ObjectMapper();
        String requestBody = null;

        try {
            requestBody = objectMapper.writeValueAsString(values);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        return Optional.ofNullable(requestBody);
    }


    /**
     * @param customerOrder
     * sends a customer order request to the server
     */
    @Override
    public void save(CustomerOrder customerOrder) {
        Optional<String> optionalRequestBody = toJson(customerOrder);
        if (!optionalRequestBody.isPresent()) {
            System.out.println("CustomerOrder could not be converted to json");
            return;
        } else {
            String requestBody = optionalRequestBody.get();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create("http://localhost:8080/webapi/myresource/customerorder"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    /**
     * @param id
     *
     * @return
     * get a specific order by id from the server
     */
    @Override
    public Optional<CustomerOrder> get(int id) {
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

            int customerId = jsonObject.getInt("customerOrderId");
            String firstname = jsonObject.getString("firstName");
            String lastname = jsonObject.getString("lastName");
            LocalDate birthdate = LocalDate.parse(jsonObject.getString("birthDate"));
            String email = jsonObject.getString("email");
            String phonenumber = jsonObject.getString("phoneNumber");
            String address = jsonObject.getString("address");
            double orderamount = jsonObject.getDouble("amount");
            String destinationAddress = jsonObject.getString("destinationAddress");
            String destinationPostcode = jsonObject.getString("postcode");
            String pickupAddress = jsonObject.getString("pickUpAddress");
            LocalDate deliveryDate = LocalDate.parse(jsonObject.getString("deliveryDate"));
            boolean hazardous = jsonObject.getBoolean("hazardous");
            double proposedPrice = jsonObject.getDouble("proposedPrice");
            String orderStatus = jsonObject.getString("orderStatus");


            CustomerOrder customer = new CustomerOrder(customerId, firstname, lastname, birthdate, email,
                    phonenumber, address, pickupAddress, destinationAddress, destinationPostcode, orderamount,
                    deliveryDate, hazardous, proposedPrice, orderStatus);

            return Optional.of(customer);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * @return
     * gets all customer orders from the server and saves them in a collection
     */
    @Override
    public Optional<Collection<CustomerOrder>> getAll() {
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
            Type type = TypeToken.getParameterized(ArrayList.class, CustomerOrder.class).getType();
            List<CustomerOrder> json = gson.fromJson(response.body(), type);
            System.out.println("-----------------------" +json);
            ObservableList<CustomerOrder> list = FXCollections.observableArrayList(json);

            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * @param customerOrder to be updated in the database
     *                      Convert java object into json object
     *                      Sends json object to the rest api
     */
    @Override
    public void update(CustomerOrder customerOrder) {
        Optional<String> optionalRequestBody = toJson(customerOrder);
        System.out.println(optionalRequestBody);
        if (!optionalRequestBody.isPresent()) {
            System.out.println("CustomerOrder could not be converted to json");
            return;
        } else {
            String requestBody = optionalRequestBody.get();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create("http://localhost:8080/webapi/myresource/customerorder/update"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        Optional<CustomerOrder> optionalRequestBody = get(id);
        Optional<String> requestBody = toJson(optionalRequestBody.get());
        System.out.println(requestBody);
        if (!requestBody.isPresent()) {
            System.out.println("CustomerOrder could not be converted to json");
        } else {
            String requestB = requestBody.get();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create("http://localhost:8080/webapi/myresource/customerorder/delete"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestB))
                    .build();

            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


