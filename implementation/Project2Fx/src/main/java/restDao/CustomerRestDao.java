package restDao;
/*
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.AccountantOrder;
import entities.Customer;
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

public class CustomerRestDao implements DAOlite<Customer>{
    private static final String API_URL = "http://localhost:8080/webapi/myresource/customer";


    @Override
    public void save(Customer e){
        var values = new HashMap<String, String>() {{

            put("firstName", "" + e.getFirstName());
            put("lastName", "" + e.getLastName());
            put("birthDate", "" + e.getBirthDate());
            put("email", "" + e.getEmail());
            put("phoneNumber", "" + e.getPhoneNumber());
            put("address", "" + e.getAddress());
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
                .uri(URI.create("http://localhost:8080/webapi/myresource/customer"))
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
    public Optional<Customer> get(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<Customer>> getAll() {
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
            ObservableList<Customer> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject a = (JSONObject) o;

                int id = a.getInt("id");
                String firstname = a.getString("firstName");
                String lastname = a.getString("lastName");
                LocalDate birthdate = LocalDate.parse(a.getString("birthDate"));
                String email = a.getString("email");
                String phonenumber = a.getString("phoneNumber");
                String address = a.getString("address");


                Customer customer = new Customer(id, firstname, lastname, email, birthdate, phonenumber, address);

                list.add(customer);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Customer update(Customer e) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    public static void main(String[] args) throws Exception {
        CustomerRestDao c = new CustomerRestDao();


    }
}


*/