package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.AccountantOrder;
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

public class AccountantOrderRestDao implements DAOlite<AccountantOrder> {


    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/accountantorder";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/accountantorder/";

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

    public static void main(String[] args) throws Exception {
        AccountantOrder a = new AccountantOrder(3, 3, 3000.00, "Street123", 123456,
                "Pickup123", LocalDate.parse("2002-12-12"), true, "D@test", 250.00);

        AccountantOrderRestDao ar = new AccountantOrderRestDao();
        System.out.println(ar.get(1));


    }

    @POST
    @Path("accountantorder")
    @Override
    public void save(AccountantOrder accountantOrder) {

        var values = new HashMap<String, String>() {{
            put("orderId", "" + accountantOrder.getOrderId());
            put("customerId", "" + accountantOrder.getCustomerId());
            put("amount", "" + accountantOrder.getAmount());
            put("destinationAddress", "" + accountantOrder.getDestinationAddress());
            put("destinationPostcode", "" + accountantOrder.getDestinationPostcode());
            put("pickupAddress", "" + accountantOrder.getPickupAddress());
            put("deliveryDate", "" + accountantOrder.getDeliveryDate());
            put("hazardous", "" + accountantOrder.isHazardous());
            put("email", "" + accountantOrder.getEmail());
            put("totalPrice", "" + accountantOrder.getTotalPrice());
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
                .uri(URI.create("http://localhost:8080/webapi/myresource/accountantorder"))
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
    public Optional<AccountantOrder> get(int id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(GET_URL + id))
                .build();

        HttpResponse<String> response = null;
        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());


            int orderId = jsonObject.getInt("orderId");
            int customerId = jsonObject.getInt("customerId");
            double amount = jsonObject.getDouble("amount");
            String destinationAddress = jsonObject.getString("destinationAddress");
            int postCode = jsonObject.getInt("destinationPostcode");
            String pickUpAddress = jsonObject.getString("pickupAddress");
            LocalDate deliveryDate = LocalDate.parse(jsonObject.getString("deliveryDate"));
            boolean hazardous = jsonObject.getBoolean("hazardous");
            String email = jsonObject.getString("email");
            double totalPrice = jsonObject.getDouble("totalPrice");


            AccountantOrder accountantOrder = new AccountantOrder(orderId, customerId, amount, destinationAddress, postCode,
                    pickUpAddress, deliveryDate, hazardous, email, totalPrice);

            return Optional.of(accountantOrder);


        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }


    }

    @Override
    public Optional<Collection<AccountantOrder>> getAll() {
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
            ObservableList<AccountantOrder> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                int orderId = jsonObject.getInt("orderId");
                int customerId = jsonObject.getInt("customerId");
                double amount = jsonObject.getDouble("amount");
                String destinationAddress = jsonObject.getString("destinationAddress");
                int postCode = jsonObject.getInt("destinationPostcode");
                String pickUpAddress = jsonObject.getString("pickupAddress");
                LocalDate deliveryDate = LocalDate.parse(jsonObject.getString("deliveryDate"));
                boolean hazardous = jsonObject.getBoolean("hazardous");
                String email = jsonObject.getString("email");
                double totalPrice = jsonObject.getDouble("totalPrice");


                AccountantOrder accountantOrder = new AccountantOrder(orderId, customerId, amount, destinationAddress, postCode,
                        pickUpAddress, deliveryDate, hazardous, email, totalPrice);

                list.add(accountantOrder);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public AccountantOrder update(AccountantOrder accountantOrder) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}



