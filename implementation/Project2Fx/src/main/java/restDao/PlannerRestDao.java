package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.CustomerOrder;
import entities.Driver;
import entities.Planner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

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

public class PlannerRestDao implements DAOlite<Planner>{
    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/planner";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/planner/";


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
                .uri(URI.create(GET_ALL_URL))
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
                .uri(URI.create(GET_ALL_URL))
                .build();

        HttpResponse<String> response = null;
        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONArray jsonArray = new JSONArray(response.body());


            GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter( LocalDate.class, new ConvertLocalDate() );

            Gson gson = gsonBuilder.create();
            Type type = TypeToken.getParameterized(ArrayList.class, Planner.class).getType();
            List<Planner> json = gson.fromJson(response.body(), type);
            System.out.println("-----------------------" +json);
            ObservableList<Planner> list = FXCollections.observableArrayList(json);


            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void update(Planner planner) {

    }

    @Override
    public void delete(int id) {

    }
}
