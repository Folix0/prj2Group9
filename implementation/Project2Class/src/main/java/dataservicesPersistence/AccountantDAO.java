package dataservicesPersistence;

import connection.JdbcConnection;
import entities.Accountant;


import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountantDAO implements DAOlite<Accountant, Integer> {

    private static final Logger LOGGER = Logger.getLogger(AccountantDAO.class.getName());
    private final Optional<Connection> connection;

    public AccountantDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Accountant> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Accountant> accountant = Optional.empty();
            String sql = "SELECT * FROM accountant WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int accountantId = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    LocalDate birthDate = rs.getDate("birthdate").toLocalDate();
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");


                    accountant = Optional.of(new Accountant(accountantId, firstName, lastName, email, birthDate, phoneNumber, address));
                    LOGGER.log(Level.INFO, "Found {0} in database", accountant.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return accountant;
        });
    }


    @Override
    public Collection<Accountant> getAll() {
        List<Accountant> accountants = new ArrayList<>();
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


                    Accountant accountant = new Accountant(customerId, firstName, lastName, email, birthDate, phoneNumber, address);

                    accountants.add(accountant);

                    LOGGER.log(Level.INFO, "Found {0} in database", accountants);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return accountants;
    }


    @Override
    public Optional<Integer> save(Accountant accountant) {
        String message = "The Accountant to be added should not be null";
        Accountant nonNullUser = Objects.requireNonNull(accountant, message);
        String sql = "INSERT INTO "
                + "accountant(firstname,lastname,birthdate,email,phonenumber,address)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

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
    public void update(Accountant accountant) {
        String message = "The Accountant to be updated should not be null";
        Accountant nonNullUser = Objects.requireNonNull(accountant, message);
        String sql = "UPDATE accountant "
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

                LOGGER.log(Level.INFO, "Was the Accountant updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void delete(Accountant accountant) {
        String message = "The Accountant to be deleted should not be null";
        Accountant nonNullUser = Objects.requireNonNull(accountant, message);
        String sql = "DELETE FROM accountant WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Accountant deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
