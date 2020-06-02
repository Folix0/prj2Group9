package dataservicesPersistence;
/*

import connection.JdbcConnection;
import entities.AccountantOrder;
import entities.Order;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class OrderDAO implements DAOlite<Order, Integer> {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private final Optional<Connection> connection;

    public OrderDAO() {
        this.connection = JdbcConnection.getConnection();
    }


    @Override
    public Optional<Order> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Order> order = Optional.empty();
            String sql = "SELECT * FROM aorder WHERE orderId = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {
                    int orderId = rs.getInt("orderid");
                    double orderAmount = rs.getDouble("orderamount");
                    String destinationAddress = rs.getString("destinationaddress");
                    String destinationPostcode = rs.getString("destinationpostcode");
                    String pickupAddress = rs.getString("pickupaddress");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();
                    boolean isHazardous = rs.getBoolean("ishazardous");
                    double proposedPrice = rs.getDouble("proposedprice");


                    order = Optional.of(new Order(orderId, pickupAddress, destinationAddress,
                            destinationPostcode, orderAmount, deliveryDate, isHazardous,proposedPrice));


                    LOGGER.log(Level.INFO, "Found {0} in database", order.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return order;
        });
    }

    @Override
    public Collection<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM aorder";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {

                    int orderId = rs.getInt("orderid");
                    double orderAmount = rs.getDouble("orderamount");
                    String destinationAddress = rs.getString("destinationaddress");
                    String destinationPostcode = rs.getString("destinationpostcode");
                    String pickupAddress = rs.getString("pickupaddress");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();
                    boolean isHazardous = rs.getBoolean("ishazardous");
                    double proposedPrice = rs.getDouble("proposedprice");

                    Order user = new Order(orderId, pickupAddress, destinationAddress,
                            destinationPostcode, orderAmount, deliveryDate, isHazardous,proposedPrice);

                    orders.add(user);

                    LOGGER.log(Level.INFO, "Found {0} in database", user);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return orders;
    }


    @Override
    public Optional<Integer> save(Order order) {
        String message = "The Order to be added should not be null";
        Order nonNullUser = Objects.requireNonNull(order, message);
        String sql = "INSERT INTO "
                + "aorder(orderamount,destinationaddress,destinationpostcode,pickupaddress,deliverydate,ishazardous,proposedprice)"
                + "VALUES(?,?,?,?,?,?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setDouble(1, nonNullUser.getAmount());
                statement.setString(2, nonNullUser.getDestinationAddress());
                statement.setString(3, nonNullUser.getPostcode());
                statement.setString(4, nonNullUser.getPickUpAddress());
                statement.setDate(5, Date.valueOf(nonNullUser.getDeliveryDate()));
                statement.setBoolean(6, nonNullUser.isHazardous());
                statement.setDouble(7, nonNullUser.getProposedPrice());


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
    public void update(Order order) {
        String message = "The Order to be updated should not be null";
        Order nonNullUser = Objects.requireNonNull(order, message);
        String sql = "UPDATE aorder "
                + "SET "
                + "orderamount = ?, "
                + "destinationaddress = ?, "
                + "destinationpostcode = ?, "
                + "pickupaddress = ?, "
                + "deliverydate = ?, "
                + "ishazardous = ?, "
                + "proposedprice = ? "
                + "WHERE "
                + "orderid = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setDouble(1, nonNullUser.getAmount());
                statement.setString(2, nonNullUser.getDestinationAddress());
                statement.setString(3, nonNullUser.getPostcode());
                statement.setString(4, nonNullUser.getPickUpAddress());
                statement.setDate(5, Date.valueOf(nonNullUser.getDeliveryDate()));
                statement.setBoolean(6, nonNullUser.isHazardous());
                statement.setDouble(7, nonNullUser.getProposedPrice());

                statement.setInt(8, nonNullUser.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Order updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void delete(Order order) {
        String message = "The customer to be deleted should not be null";
        Order nonNullUser = Objects.requireNonNull(order, message);
        String sql = "DELETE FROM aorder WHERE orderid = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Order deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
*/