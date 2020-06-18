package restDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import entities.CustomerOrder;
import entities.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class DriverRestDao implements DAOlite<Driver>{

    private static final String GET_ALL_URL = "http://localhost:8080/webapi/myresource/driver";
    private static final String GET_URL = "http://localhost:8080/webapi/myresource/driver/";

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
    @Path("driver")
    @Override
    public void save(Driver driver)  {
        var values = new HashMap<String, String>() {{

            put("firstName", "" + driver.getFirstName());
            put("lastName", "" + driver.getLastName());
            put("birthDate", "" + driver.getBirthDate());
            put("email", "" + driver.getEmail());
            put("phoneNumber", "" + driver.getPhoneNumber());
            put("address", "" + driver.getAddress());
            put("isAvailable", "" + driver.isAvailable());
            put("hasHazardousLicense", "" + driver.isHasHazardousLicense());
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
                .uri(URI.create("http://localhost:8080/webapi/myresource/driver"))
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
    public Optional<Driver> get(int id) {
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


            int driverId = jsonObject.getInt("id");
            String firstname = jsonObject.getString("firstName");
            String lastname = jsonObject.getString("lastName");
            LocalDate birthdate = LocalDate.parse(jsonObject.getString("birthDate"));
            String email = jsonObject.getString("email");
            String phonenumber = jsonObject.getString("phoneNumber");
            String address = jsonObject.getString("address");
            boolean isAvailable = jsonObject.getBoolean("isAvailable");
            boolean licence = jsonObject.getBoolean("hasHazardousLicense");

            Driver driver = new Driver(driverId,isAvailable,licence,firstname,lastname,
                    birthdate,email,phonenumber,address);


            return Optional.of(driver);


            //altered variable name
        } catch (InterruptedException | IOException ioException) {
            ioException.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Collection<Driver>> getAll() {
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
            ObservableList<Driver> list = FXCollections.observableArrayList();

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                int id = jsonObject.getInt("id");
                String firstname = jsonObject.getString("firstName");
                String lastname = jsonObject.getString("lastName");
                LocalDate birthdate = LocalDate.parse(jsonObject.getString("birthDate"));
                String email = jsonObject.getString("email");
                String phonenumber = jsonObject.getString("phoneNumber");
                String address = jsonObject.getString("address");
                boolean isAvailable = jsonObject.getBoolean("available");
                boolean licence = jsonObject.getBoolean("hasHazardousLicense");


                Driver driver = new Driver(id, isAvailable, licence, firstname,
                        lastname, birthdate, email, phonenumber,address);

                  list.add(driver);

            }
            return Optional.of(list);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void update(Driver driver) {

    }

    @Override
    public void delete(int id) {

    }
}
