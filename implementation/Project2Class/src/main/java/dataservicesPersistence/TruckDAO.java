package dataservicesPersistence;

import connection.JdbcConnection;
import entities.Trailer;
import entities.Truck;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TruckDAO implements DAOlite<Truck, Integer> {


    private static final Logger LOGGER = Logger.getLogger(TruckDAO.class.getName());
    private final Optional<Connection> connection;

    public TruckDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Truck> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Truck> truck = Optional.empty();
            String sql = "SELECT * FROM truck WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int truckId = rs.getInt("id");
                    String licensePlate = rs.getString("trucklicenceplate");
                    String pickupLocation = rs.getString("truckpickuplocation");
                    double truckWeight = rs.getDouble("truckweight");
                    int mileage = rs.getInt("mileage");
                    Boolean isAvailable = rs.getBoolean("isavailable");
                    LocalDate maintenanceCheckdate = rs.getDate("maintenancecheckdate").toLocalDate();


                    truck = Optional.of(new Truck(truckId, licensePlate, isAvailable, truckWeight, mileage,
                            pickupLocation, maintenanceCheckdate));

                    LOGGER.log(Level.INFO, "Found {0} in database", truck.get());
                }
            }
            catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return truck;
        });
    }


    @Override
    public Collection<Truck> getAll() {
        List<Truck> trucks = new ArrayList<>();
        String sql = "SELECT * FROM truck";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {
                    int truckId = rs.getInt("id");
                    String licensePlate = rs.getString("trucklicenceplate");
                    String pickupLocation = rs.getString("truckpickuplocation");
                    double truckWeight = rs.getDouble("truckweight");
                    int mileage = rs.getInt("mileage");
                    Boolean isAvailable = rs.getBoolean("isavailable");
                    LocalDate maintenanceCheckdate = rs.getDate("maintenancecheckdate").toLocalDate();


                    Truck truck = new Truck(truckId, licensePlate, isAvailable, truckWeight, mileage,
                            pickupLocation, maintenanceCheckdate);

                    trucks.add(truck);

                    LOGGER.log(Level.INFO, "Found {0} in database", trucks);
                }

            }
            catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return trucks;
    }

    @Override
    public Optional<Integer> save(Truck truck) {
        String message = "The Truck to be added should not be null";
        Truck nonNullUser = Objects.requireNonNull(truck, message);
        String sql = "INSERT INTO "
                + "truck(trucklicenceplate,truckpickuplocation,truckweight,mileage,isavailable,maintenancecheckdate)"
                + "VALUES(?,?,?,?,?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, nonNullUser.getTruckLicensePlate());
                statement.setString(2, nonNullUser.getTruckPickupLocation());
                statement.setDouble(3, nonNullUser.getTruckWeight());
                statement.setInt(4,nonNullUser.getMileage());
                statement.setBoolean(5, nonNullUser.isAvailable());
                statement.setDate(6, Date.valueOf(nonNullUser.getMaintenanceCheckdate()));

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
            }
            catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }


    @Override
    public void update(Truck truck) {
        String message = "The Truck to be updated should not be null";
        Truck nonNullUser = Objects.requireNonNull(truck, message);
        String sql = "UPDATE truck "
                + "SET "
                + "trucklicenceplate = ?, "
                + "truckpickuplocation = ?, "
                + "truckweight = ?, "
                + "mileage = ?, "
                + "isavailable = ?, "
                + "maintenancecheckdate = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql))
            {

                statement.setString(1, nonNullUser.getTruckLicensePlate());
                statement.setString(2, nonNullUser.getTruckPickupLocation());
                statement.setDouble(3, nonNullUser.getTruckWeight());
                statement.setInt(4,nonNullUser.getMileage());
                statement.setBoolean(5, nonNullUser.isAvailable());
                statement.setDate(6, Date.valueOf(nonNullUser.getMaintenanceCheckdate()));

                statement.setInt(7, nonNullUser.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Truck updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            }
            catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }



    @Override
    public void delete(Truck truck) {
        String message = "The Truck to be deleted should not be null";
        Truck nonNullUser = Objects.requireNonNull(truck, message);
        String sql = "DELETE FROM truck WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql))
            {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Truck deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            }
            catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}


