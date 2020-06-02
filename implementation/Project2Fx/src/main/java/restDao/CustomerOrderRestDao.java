package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomerOrder;
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

public class CustomerOrderRestDao implements DAOlite<CustomerOrder> {

    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/customerorder";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/customerorder/";

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

    //altered variable name
    @POST
    @Path("customerorder")
    @Override
    public void save(CustomerOrder customerOrder) {
        var values = new HashMap<String, String>() {{

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
            put("orderstatus", "" + customerOrder.getOrderStatus());
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
                .uri(URI.create("http://localhost:8080/webapi/myresource/customerorder"))
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


            //altered variable name
        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }


    }

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

            JSONArray jsonArray = new JSONArray(response.body());
            ObservableList<CustomerOrder> list = FXCollections.observableArrayList();

            //altered variable name
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

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
                //String orderStatus = a.getString("orderStatus");


                CustomerOrder customer = new CustomerOrder(customerId, firstname, lastname, birthdate, email,
                        phonenumber, address, pickupAddress, destinationAddress, destinationPostcode, orderamount,
                        deliveryDate, hazardous, proposedPrice);

                list.add(customer);

            }
            return Optional.of(list);

            //altered variable name
        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }
    }

    //altered variable name
    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        Optional<CustomerOrder> c = get(customerOrder.getCustomerOrderId());
        c.get().setProposedPrice(customerOrder.getProposedPrice());

        return c.get();
    }


    @Override
    public void delete(int id) {

    }
}


