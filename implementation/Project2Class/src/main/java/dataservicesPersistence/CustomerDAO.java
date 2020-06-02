package dataservicesPersistence;
/*
import connection.JdbcConnection;
import entities.Customer;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO implements DAOlite<Customer, Integer> {

    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private final Optional<Connection> connection;

    public CustomerDAO() {
        this.connection = JdbcConnection.getConnection();
    }


    @Override
    public Optional<Customer> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Customer> customer = Optional.empty();
            String sql = "SELECT * FROM customer WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int customerId = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    LocalDate birthDate = rs.getDate("birthdate").toLocalDate();
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");


                    customer = Optional.of(new Customer(customerId, firstName, lastName, email, birthDate, phoneNumber, address));
                    LOGGER.log(Level.INFO, "Found {0} in database", customer.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return customer;
        });
    }

    @Override
    public Collection<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

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


                    Customer customer = new Customer(customerId, firstName, lastName, email, birthDate, phoneNumber, address);

                    customers.add(customer);

                    LOGGER.log(Level.INFO, "Found {0} in database", customers);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return customers;
    }

    @Override
    public Optional<Integer> save(Customer customer) {
        String message = "The Customer to be added should not be null";
        Customer nonNullUser = Objects.requireNonNull(customer, message);
        String sql = "INSERT INTO "
                + "customer(firstname,lastname,birthdate,email,phonenumber,address)"
                + "VALUES(?,?,?,?,?,?)";

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
    public void update(Customer customer) {
        String message = "The Customer to be updated should not be null";
        Customer nonNullUser = Objects.requireNonNull(customer, message);
        String sql = "UPDATE customer "
                + "SET "
                + "firstname = ?, "
                + "lastname = ?, "
                + "email = ?, "
                + "phonenumber = ?, "
                + "address = ? "
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

                statement.setInt(7, nonNullUser.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Customer updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void delete(Customer customer) {
        String message = "The Customer to be deleted should not be null";
        Customer nonNullUser = Objects.requireNonNull(customer, message);
        String sql = "DELETE FROM customer WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Customer deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}*/
