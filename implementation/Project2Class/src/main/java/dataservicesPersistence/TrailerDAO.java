package dataservicesPersistence;

import connection.JdbcConnection;
import entities.Trailer;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrailerDAO implements DAOlite<Trailer, Integer> {


    private static final Logger LOGGER = Logger.getLogger(TrailerDAO.class.getName());
    private final Optional<Connection> connection;

    public TrailerDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Trailer> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Trailer> trailer = Optional.empty();
            String sql = "SELECT * FROM trailer WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int trailerId = rs.getInt("id");
                    String licensePlate = rs.getString("trailerlicenceplate");
                    String pickupLocation = rs.getString("trailerpickuplocation");
                    double capacity = rs.getDouble("capacity");
                    double trailerWeight = rs.getDouble("trailerweight");
                    Boolean isCleaned = rs.getBoolean("iscleaned");
                    Boolean isAvailable = rs.getBoolean("isavailable");
                    Boolean isHazardous = rs.getBoolean("ishazardous");
                    LocalDate maintenanceCheckdate = rs.getDate("maintenancecheckdate").toLocalDate();


                    trailer = Optional.of(new Trailer(trailerId, licensePlate, isCleaned, isAvailable, isHazardous,
                            capacity, pickupLocation, maintenanceCheckdate, trailerWeight));
                    LOGGER.log(Level.INFO, "Found {0} in database", trailer.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return trailer;
        });
    }

    @Override
    public Collection<Trailer> getAll() {
        List<Trailer> trailers = new ArrayList<>();
        String sql = "SELECT * FROM trailer";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {
                    int trailerId = rs.getInt("id");
                    String licensePlate = rs.getString("trailerlicenceplate");
                    String pickupLocation = rs.getString("trailerpickuplocation");
                    double capacity = rs.getDouble("capacity");
                    double trailerWeight = rs.getDouble("trailerweight");
                    Boolean isCleaned = rs.getBoolean("iscleaned");
                    Boolean isAvailable = rs.getBoolean("isavailable");
                    Boolean isHazardous = rs.getBoolean("ishazardous");
                    LocalDate maintenanceCheckdate = rs.getDate("maintenancecheckdate").toLocalDate();


                    Trailer trailer = new Trailer(trailerId, licensePlate, isCleaned, isAvailable, isHazardous,
                            capacity, pickupLocation, maintenanceCheckdate, trailerWeight);

                    trailers.add(trailer);

                    LOGGER.log(Level.INFO, "Found {0} in database", trailers);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return trailers;
    }

    @Override
    public Optional<Integer> save(Trailer trailer) {
        String message = "The Trailer to be added should not be null";
        Trailer nonNullUser = Objects.requireNonNull(trailer, message);
        String sql = "INSERT INTO "
                + "trailer(trailerlicenceplate,trailerpickuplocation,capacity," +
                "trailerweight,iscleaned,isavailable,ishazardous,maintenancecheckdate)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, nonNullUser.getTrailerLicencePlate());
                statement.setString(2, nonNullUser.getTrailerPickupLocation());
                statement.setDouble(3, nonNullUser.getCapacity());
                statement.setDouble(4, nonNullUser.getTrailerWeight());
                statement.setBoolean(5, nonNullUser.isCleaned());
                statement.setBoolean(6, nonNullUser.isAvailable());
                statement.setBoolean(7, nonNullUser.isHazardous());
                statement.setDate(8, Date.valueOf(nonNullUser.getMaintenanceCheckdate()));

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
    public void update(Trailer trailer) {
        String message = "The Trailer to be updated should not be null";
        Trailer nonNullUser = Objects.requireNonNull(trailer, message);
        String sql = "UPDATE trailer "
                + "SET "
                + "trailerlicenceplate = ?, "
                + "trailerpickuplocation = ?, "
                + "capacity = ?, "
                + "trailerweight = ?, "
                + "iscleaned = ?, "
                + "isavailable = ?, "
                + "ishazardous = ?, "
                + "maintenancecheckdate = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, nonNullUser.getTrailerLicencePlate());
                statement.setString(2, nonNullUser.getTrailerPickupLocation());
                statement.setDouble(3, nonNullUser.getCapacity());
                statement.setDouble(4, nonNullUser.getTrailerWeight());
                statement.setBoolean(5, nonNullUser.isCleaned());
                statement.setBoolean(6, nonNullUser.isAvailable());
                statement.setBoolean(7, nonNullUser.isHazardous());
                statement.setDate(8, Date.valueOf(nonNullUser.getMaintenanceCheckdate()));

                statement.setInt(9, nonNullUser.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Trailer updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void delete(Trailer trailer) {
        String message = "The Trailer to be deleted should not be null";
        Trailer nonNullUser = Objects.requireNonNull(trailer, message);
        String sql = "DELETE FROM trailer WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Trailer deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}

