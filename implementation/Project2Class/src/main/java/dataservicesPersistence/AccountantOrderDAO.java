package dataservicesPersistence;

import connection.JdbcConnection;
import entities.AccountantOrder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountantOrderDAO implements DAOlite<AccountantOrder, Integer> {


    private static final Logger LOGGER = Logger.getLogger(AccountantOrderDAO.class.getName());
    private final Optional<Connection> connection;

    public AccountantOrderDAO() {
        this.connection = JdbcConnection.getConnection();
    }


    @Override
    public Optional<AccountantOrder> get(int orderId) {
        return connection.flatMap(conn -> {
            Optional<AccountantOrder> accountantOrder = Optional.empty();
            String sql = "SELECT * FROM accountantorder WHERE orderid = " + orderId;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int id = rs.getInt("orderid");
                    int customerId = rs.getInt("customerid");
                    double amount = rs.getDouble("amount"); //column name
                    String destinationAddress = rs.getString("destinationaddress");
                    int destinationPostcode = rs.getInt("destinationpostcode");
                    String pickupAddress = rs.getString("pickupaddress");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();
                    boolean hazardous = rs.getBoolean("hazardous");
                    String email = rs.getString("email");
                    double totalPrice = rs.getDouble("totalprice");


                    accountantOrder = Optional.of(new AccountantOrder(id, customerId, amount, destinationAddress, destinationPostcode, pickupAddress, deliveryDate, hazardous, email, totalPrice));

                    LOGGER.log(Level.INFO, "Found {0} in database", accountantOrder.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return accountantOrder;
        });
    }

    @Override
    public List<AccountantOrder> getAll() {
        List<AccountantOrder> accountantOrders = new ArrayList<>();
        String sql = "SELECT * FROM accountantorder";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {

                    int id = rs.getInt("orderid");
                    int customerId = rs.getInt("customerid");
                    double amount = rs.getDouble("amount"); //column name
                    String destinationAddress = rs.getString("destinationaddress");
                    int destinationPostcode = rs.getInt("destinationpostcode");
                    String pickupAddress = rs.getString("pickupaddress");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();
                    boolean hazardous = rs.getBoolean("hazardous");
                    String email = rs.getString("email");
                    double totalPrice = rs.getDouble("totalprice");


                    AccountantOrder user = new AccountantOrder(id, customerId, amount, destinationAddress, destinationPostcode, pickupAddress, deliveryDate, hazardous, email, totalPrice);

                    accountantOrders.add(user);

                    LOGGER.log(Level.INFO, "Found {0} in database", user);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return accountantOrders;
    }

    @Override
    public Optional<Integer> save(AccountantOrder accountantOrder) {
        String message = "The customer to be added should not be null";
        AccountantOrder nonNullUser = Objects.requireNonNull(accountantOrder, message);
        String sql = "INSERT INTO "
                + "accountantorder(customerid,amount,destinationaddress,destinationpostcode,pickupaddress,deliverydate,hazardous,email,totalprice)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, nonNullUser.getCustomerId());
                statement.setDouble(2, nonNullUser.getAmount());
                statement.setString(3, nonNullUser.getDestinationAddress());
                statement.setInt(4, nonNullUser.getDestinationPostcode());
                statement.setString(5, nonNullUser.getPickupAddress());
                statement.setDate(6, Date.valueOf(nonNullUser.getDeliveryDate()));
                statement.setBoolean(7, nonNullUser.isHazardous());
                statement.setString(8, nonNullUser.getEmail());
                statement.setDouble(9, nonNullUser.getTotalPrice());


                int numberOfInsertedRows = statement.executeUpdate();

                //Retrieve the auto-generated id
                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getInt(1));
                        }
                    }
                }

                LOGGER.log(Level.INFO, "{0} created successfully? {1}",
                        new Object[]{nonNullUser, numberOfInsertedRows > 0});
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(AccountantOrder user) {
        String message = "The customer to be updated should not be null";
        AccountantOrder nonNullUser = Objects.requireNonNull(user, message);
        String sql = "UPDATE accountantorder "
                + "SET "
                + "customerid = ?, "
                + "amount = ?, "
                + "destinationaddress = ? ,"
                + "destinationpostcode = ?, "
                + "pickupaddress = ?, "
                + "deliverydate = ?, "
                + "hazardous = ?, "
                + "email = ?, "
                + "totalprice = ? "
                + "WHERE "
                + "orderid = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getCustomerId());
                statement.setDouble(2, nonNullUser.getAmount());
                statement.setString(3, nonNullUser.getDestinationAddress());
                statement.setInt(4, nonNullUser.getDestinationPostcode());
                statement.setString(5, nonNullUser.getPickupAddress());
                statement.setDate(6, Date.valueOf(nonNullUser.getDeliveryDate()));
                statement.setBoolean(7, nonNullUser.isHazardous());
                statement.setString(8, nonNullUser.getEmail());
                statement.setDouble(9, nonNullUser.getTotalPrice());
                statement.setInt(10, nonNullUser.getOrderId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the customer updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void delete(AccountantOrder accountantOrder) {
        String message = "The customer to be deleted should not be null";
        AccountantOrder nonNullUser = Objects.requireNonNull(accountantOrder, message);
        String sql = "DELETE FROM accountantorder WHERE orderid = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getOrderId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the customer deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }



}


