package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Driver;
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

public class PlannerRestDao implements DAOlite<Planner>{
    private static final String API_URL = "http://localhost:8080/webapi/myresource/planner";

    @Override
    public void save(Planner planner) throws IOException {
        var values = new HashMap<String, String>() {{

            put("id", "" + planner.getId());
            put("firstname", "" + planner.getFirstName());
            put("lastname", "" + planner.getLastName());
            put("birthdate", "" + planner.getBirthDate());
            put("email", "" + planner.getEmail());
            put("phonenumber", "" + planner.getPhoneNumber());
            put("address", "" + planner.getAddress());
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
    public Optional<Planner> get(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<Planner>> getAll() {
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
            ObservableList<Planner> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                int id = jsonObject.getInt("id");
                String firstname = jsonObject.getString("firstname");
                String lastname = jsonObject.getString("lastname");
                LocalDate birthdate = LocalDate.parse(jsonObject.getString("birthdate"));
                String email = jsonObject.getString("email");
                String phonenumber = jsonObject.getString("phonenumber");
                String address = jsonObject.getString("address");



                Planner planner = new Planner(id, firstname, lastname, email, birthdate, phonenumber, address);

                list.add(planner);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Planner update(Planner planner) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
