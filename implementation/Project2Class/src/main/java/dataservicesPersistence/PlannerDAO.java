package dataservicesPersistence;

import connection.JdbcConnection;

import entities.Planner;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlannerDAO implements DAOlite<Planner, Integer> {

    private static final Logger LOGGER = Logger.getLogger(PlannerDAO.class.getName());
    private final Optional<Connection> connection;

    public PlannerDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Planner> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Planner> planner = Optional.empty();
            String sql = "SELECT * FROM planner WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int plannerId = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    LocalDate birthDate = rs.getDate("birthdate").toLocalDate();
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");


                    planner = Optional.of(new Planner(plannerId, firstName, lastName, email, birthDate, phoneNumber, address));
                    LOGGER.log(Level.INFO, "Found {0} in database", planner.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return planner;
        });
    }


    @Override
    public Collection<Planner> getAll() {
        List<Planner> planners = new ArrayList<>();
        String sql = "SELECT * FROM planner";

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


                    Planner customer = new Planner(customerId, firstName, lastName, email, birthDate, phoneNumber, address);

                    planners.add(customer);

                    LOGGER.log(Level.INFO, "Found {0} in database", planners);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return planners;
    }


    @Override
    public Optional<Integer> save(Planner planner) {
        String message = "The Customer to be added should not be null";
        Planner nonNullUser = Objects.requireNonNull(planner, message);
        String sql = "INSERT INTO "
                + "planner(firstname,lastname,birthdate,email,phonenumber,address)"
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
    public void update(Planner planner) {
        String message = "The Customer to be updated should not be null";
        Planner nonNullUser = Objects.requireNonNull(planner, message);
        String sql = "UPDATE planner "
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
    public void delete(Planner planner) {
        String message = "The Customer to be deleted should not be null";
        Planner nonNullUser = Objects.requireNonNull(planner, message);
        String sql = "DELETE FROM planner WHERE id = ?";

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
}
