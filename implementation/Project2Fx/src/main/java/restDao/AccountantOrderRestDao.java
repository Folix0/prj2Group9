package restDao;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import entities.AccountantOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.Json;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AccountantOrderRestDao implements DAOlite<AccountantOrder> {


    private static final String API_URL = "http://localhost:8080/webapi/myresource/accountantorder/1";

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


    @POST
    @Path("accountantorder")
    @Override
    public void save(AccountantOrder e) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");


        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public AccountantOrder get(int id) throws Exception {
        Collection<AccountantOrder> a = getAll();
        AccountantOrder accountant = null;

        for(AccountantOrder acc : a){
            if(acc.getOrderId() == id){
                accountant = acc;
            }
        }

        return accountant;
    }

    @Override
    public Collection<AccountantOrder> getAll() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(response.body());
        ObservableList<AccountantOrder> list = FXCollections.observableArrayList();

        for (Object o : jsonArray) {
            JSONObject a = (JSONObject) o;

            int orderId = a.getInt("orderId");
            int customerId = a.getInt("customerId");
            double amount = a.getDouble("amount");
            String destinationAddress = a.getString("destinationAddress");
            int postCode = a.getInt("destinationPostcode");
            String pickUpAddress = a.getString("pickupAddress");
            LocalDate deliveryDate = LocalDate.parse(a.getString("deliveryDate"));
            boolean hazardous = a.getBoolean("hazardous");
            String email = a.getString("email");
            double totalPrice = a.getDouble("totalPrice");


            AccountantOrder acc = new AccountantOrder(orderId,customerId, amount, destinationAddress, postCode,
                    pickUpAddress, deliveryDate, hazardous, email, totalPrice);

            list.add(acc);

        }
        return list;
    }

    @Override
    public AccountantOrder update(AccountantOrder e) {
        return null;
    }

    @Override
    public void delete(int id) {

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

    }
}



