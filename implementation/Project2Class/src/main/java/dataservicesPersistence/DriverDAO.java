package dataservicesPersistence;

import connection.JdbcConnection;
import entities.Accountant;
import entities.Driver;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverDAO implements DAOlite<Driver,Integer> {

    private static final Logger LOGGER = Logger.getLogger(DriverDAO.class.getName());
    private final Optional<Connection> connection;

    public DriverDAO() {
        this.connection = JdbcConnection.getConnection();
    }
    @Override
    public Optional<Driver> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Driver> driver = Optional.empty();
            String sql = "SELECT * FROM driver WHERE id = "+ id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int driverId = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    LocalDate birthDate = rs.getDate("birthdate").toLocalDate();
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");
                    boolean isAvailable = rs.getBoolean("isavailable");
                    boolean hasHazardousLicense = rs.getBoolean("hashazardouslicense");


                    driver = Optional.of(new Driver( driverId,isAvailable,hasHazardousLicense,
                            firstName,lastName,birthDate,email,phoneNumber,address));

                    LOGGER.log(Level.INFO, "Found {0} in database", driver.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return driver;
        });
    }

    @Override
    public Collection<Driver> getAll() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM driver";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {

                    int driverId = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    LocalDate birthDate = rs.getDate("birthdate").toLocalDate();
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");
                    boolean isAvailable = rs.getBoolean("isavailable");
                    boolean hasHazardousLicense = rs.getBoolean("hashazardouslicense");


                    Driver driver = new Driver(driverId,isAvailable,hasHazardousLicense,
                            firstName,lastName,birthDate,email,phoneNumber,address);

                    drivers.add(driver);

                    LOGGER.log(Level.INFO, "Found {0} in database", drivers);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return drivers;
    }

    @Override
    public Optional<Integer> save(Driver driver) {
        String message = "The Driver to be added should not be null";
        Driver nonNullUser = Objects.requireNonNull(driver, message);
        String sql = "INSERT INTO "
                + "driver(firstname,lastname,birthdate,email,phonenumber,address," +
                "isavailable,hashazardouslicense)"
                + "VALUES(?,?,?,?,?,?,?,?)";

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
                statement.setBoolean(7,nonNullUser.isAvailable());
                statement.setBoolean(8,nonNullUser.isHasHazardousLicense());


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
    public void update(Driver driver) {
            String message = "The Driver to be updated should not be null";
            Driver nonNullUser = Objects.requireNonNull(driver, message);
            String sql = "UPDATE driver "
                    + "SET "
                    + "firstname = ?, "
                    + "lastname = ?, "
                    + "birthdate = ?, "
                    + "email = ?, "
                    + "phonenumber = ?, "
                    + "address = ?, "
                    + "isavailable = ?, "
                    + "hashazardouslicense = ? "
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
                    statement.setBoolean(7,nonNullUser.isAvailable());
                    statement.setBoolean(8,nonNullUser.isHasHazardousLicense());

                    statement.setInt(9, nonNullUser.getId());

                    int numberOfUpdatedRows = statement.executeUpdate();

                    LOGGER.log(Level.INFO, "Was the Driver updated successfully? {0}",
                            numberOfUpdatedRows > 0);

                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            });
        }


    @Override
    public void delete(Driver driver) {
        String message = "The Driver to be deleted should not be null";
        Driver nonNullUser = Objects.requireNonNull(driver, message);
        String sql = "DELETE FROM driver WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Driver deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}

