package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Accountant;
import entities.Driver;
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

public class AccountantRestDao implements DAOlite<Accountant>{
    private static final String API_URL = "http://localhost:8080/webapi/myresource/accountant";

    @Override
    public void save(Accountant accountant) throws IOException {
        var values = new HashMap<String, String>() {{

            put("id", "" + accountant.getId());
            put("firstname", "" + accountant.getFirstName());
            put("lastname", "" + accountant.getLastName());
            put("birthdate", "" + accountant.getBirthDate());
            put("email", "" + accountant.getEmail());
            put("phonenumber", "" + accountant.getPhoneNumber());
            put("address", "" + accountant.getAddress());
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
    public Optional<Accountant> get(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<Accountant>> getAll() {
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
            ObservableList<Accountant> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                int id = jsonObject.getInt("id");
                String firstname = jsonObject.getString("firstname");
                String lastname = jsonObject.getString("lastname");
                LocalDate birthdate = LocalDate.parse(jsonObject.getString("birthdate"));
                String email = jsonObject.getString("email");
                String phonenumber = jsonObject.getString("phonenumber");
                String address = jsonObject.getString("address");



                Accountant driver = new Accountant(id, firstname, lastname, email, birthdate, phonenumber, address);

                list.add(driver);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void update(Accountant accountant) {

    }

    @Override
    public void delete(int id) {

    }

}



