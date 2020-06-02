package dataservicesPersistence;

import connection.JdbcConnection;
import entities.CustomerOrder;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerOrderDAO implements DAOlite<CustomerOrder, Integer> {

    private static final Logger LOGGER = Logger.getLogger(CustomerOrderDAO.class.getName());
    private final Optional<Connection> connection;

    public CustomerOrderDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<CustomerOrder> get(int id) {
        return connection.flatMap(conn -> {
            Optional<CustomerOrder> customerOrder = Optional.empty();
            String sql = "SELECT * FROM customerorder WHERE id = " + id;

            try (Statement statement = conn.createStatement()){
                 ResultSet rs = statement.executeQuery(sql);

                if (rs.next()) {

                    int customerId = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    LocalDate birthDate = rs.getDate("birthdate").toLocalDate();
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");
                    double amount = rs.getDouble("orderamount");
                    String destinationAddress = rs.getString("destinationaddress");
                    String destinationPostcode = rs.getString("destinationpostcode");
                    String pickupAddress = rs.getString("pickupaddress");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();
                    boolean isHazardous = rs.getBoolean("ishazardous");
                    double proposedPrice = rs.getDouble("proposedprice");
                    String orderStatus = rs.getString("orderstatus");


                    customerOrder = Optional.of(new CustomerOrder(customerId, firstName, lastName,
                            birthDate, email, phoneNumber, address, pickupAddress, destinationAddress,
                            destinationPostcode, amount, deliveryDate, isHazardous, proposedPrice, orderStatus));

                    LOGGER.log(Level.INFO, "Found {0} in database", customerOrder.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return customerOrder;
        });
    }


    @Override
    public Collection<CustomerOrder> getAll() {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        String sql = "SELECT * FROM customerorder";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {

                    int customerId = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    LocalDate birthDate = rs.getDate("birthdate").toLocalDate();
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");
                    double amount = rs.getDouble("orderamount");
                    String destinationAddress = rs.getString("destinationaddress");
                    String destinationPostcode = rs.getString("destinationpostcode");
                    String pickupAddress = rs.getString("pickupaddress");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();
                    boolean isHazardous = rs.getBoolean("ishazardous");
                    double proposedPrice = rs.getDouble("proposedprice");
                    String orderStatus = rs.getString("orderstatus");


                    CustomerOrder customer = new CustomerOrder(customerId, firstName, lastName,
                            birthDate, email, phoneNumber, address, pickupAddress, destinationAddress,
                            destinationPostcode, amount, deliveryDate, isHazardous, proposedPrice, orderStatus);

                    customerOrders.add(customer);

                    LOGGER.log(Level.INFO, "Found {0} in database", customerOrders);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return customerOrders;
    }


    @Override
    public Optional<Integer> save(CustomerOrder customerOrder) {
        String message = "The Customer to be added should not be null";
        CustomerOrder nonNullUser = Objects.requireNonNull(customerOrder, message);
        String sql = "INSERT INTO "
                + "customerorder(firstname,lastname,birthdate,email,phonenumber," +
                "address,orderamount,destinationaddress,destinationpostcode," +
                "pickupaddress,deliverydate,ishazardous,proposedprice,orderstatus)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, nonNullUser.getFirstName());
                statement.setString(2, nonNullUser.getLastName());
                statement.setDate(3, Date.valueOf(nonNullUser.getBirthDate()));
                statement.setString(4, nonNullUser.getEmail());
                statement.setString(5, nonNullUser.getPhoneNumber());
                statement.setString(6, nonNullUser.getAddress());
                statement.setDouble(7, nonNullUser.getAmount());
                statement.setString(8, nonNullUser.getDestinationAddress());
                statement.setString(9, nonNullUser.getPostcode());
                statement.setString(10, nonNullUser.getPickUpAddress());
                statement.setDate(11, Date.valueOf(nonNullUser.getDeliveryDate()));
                statement.setBoolean(12, nonNullUser.isHazardous());
                statement.setDouble(13, nonNullUser.getProposedPrice());
                statement.setString(14, nonNullUser.getOrderStatus());


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
    public void update(CustomerOrder customerOrder) {
        String message = "The Customer to be updated should not be null";
        CustomerOrder nonNullUser = Objects.requireNonNull(customerOrder, message);
        String sql = "UPDATE customerorder "
                + "SET "
                + "firstname = ?, "
                + "lastname = ?, "
                + "email = ?, "
                + "phonenumber = ?, "
                + "address = ? "
                + "orderamount = ?, "
                + "destinationaddress = ?, "
                + "destinationpostcode = ?, "
                + "pickupaddress = ?, "
                + "deliverydate = ?, "
                + "ishazardous = ?, "
                + "proposedprice = ?, "
                + "orderstatus = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, nonNullUser.getFirstName());
                statement.setString(2, nonNullUser.getLastName());
                statement.setDate(3, Date.valueOf(nonNullUser.getBirthDate()));
                statement.setString(4, nonNullUser.getEmail());
                statement.setString(5, nonNullUser.getPhoneNumber());
                statement.setString(6, nonNullUser.getAddress());
                statement.setDouble(7, nonNullUser.getAmount());
                statement.setString(8, nonNullUser.getDestinationAddress());
                statement.setString(9, nonNullUser.getPostcode());
                statement.setString(10, nonNullUser.getPickUpAddress());
                statement.setDate(11, Date.valueOf(nonNullUser.getDeliveryDate()));
                statement.setBoolean(12, nonNullUser.isHazardous());
                statement.setDouble(13, nonNullUser.getProposedPrice());
                statement.setString(14, nonNullUser.getOrderStatus());

                statement.setInt(15, nonNullUser.getCustomerOrderId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Customer updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void delete(CustomerOrder customerOrder) {

        String message = "The customer to be deleted should not be null";
        CustomerOrder nonNullUser = Objects.requireNonNull(customerOrder, message);
        String sql = "DELETE FROM customerorder WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getCustomerOrderId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Order deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}

