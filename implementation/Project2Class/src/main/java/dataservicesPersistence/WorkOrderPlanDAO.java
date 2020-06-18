package dataservicesPersistence;

import connection.JdbcConnection;
import entities.WorkOrderPlan;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkOrderPlanDAO implements DAOlite<WorkOrderPlan, Integer> {

    private static final Logger LOGGER = Logger.getLogger(WorkOrderPlanDAO.class.getName());
    private final Optional<Connection> connection;

    public WorkOrderPlanDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional get(int id) {
        return connection.flatMap(conn -> {
            Optional<WorkOrderPlan> workOrderPlan = Optional.empty();
            String sql = "SELECT * FROM workorderplan WHERE id = " + id;

            try (Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);

                if (rs.next()) {
                    int workId = rs.getInt("id");
                    int orderId = rs.getInt("orderid");
                    int driverId = rs.getInt("driverid");
                    String trailerLicencePlate = rs.getString("trailerlicenceplate");
                    String truckLicencePlate = rs.getString("trucklicenceplate");
                    String trailerPickupLocation = rs.getString("trailerpickuplocation");
                    double amount = rs.getDouble("loadamount");
                    boolean hazardousLiquid = rs.getBoolean("hazardousliquid");
                    boolean trailerCleaned = rs.getBoolean("trailercleaned");
                    String deliveryAddress = rs.getString("deliveryaddress");
                    String deliveryPostcode = rs.getString("deliverypostcode");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();

                    workOrderPlan = Optional.of(new WorkOrderPlan(workId, orderId, driverId,
                            trailerLicencePlate, truckLicencePlate, trailerPickupLocation, amount, hazardousLiquid, trailerCleaned,
                            deliveryAddress, deliveryPostcode, deliveryDate));


                    LOGGER.log(Level.INFO, "Found {0} in database", workOrderPlan.get()); //optional get
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return workOrderPlan;
        });
    }


    @Override
    public Collection getAll() {
        List<WorkOrderPlan> workOrderPlans = new ArrayList<>();
        String sql = "SELECT * FROM workorderplan";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {

                    int workId = rs.getInt("id");
                    int orderId = rs.getInt("orderid");
                    int driverId = rs.getInt("driverid");
                    String trailerLicencePlate = rs.getString("trailerlicenceplate");
                    String truckLicencePlate = rs.getString("trucklicenceplate");
                    String trailerPickupLocation = rs.getString("trailerpickuplocation");
                    double amount = rs.getDouble("loadamount");
                    boolean hazardousLiquid = rs.getBoolean("hazardousliquid");
                    boolean trailerCleaned = rs.getBoolean("trailercleaned");
                    String deliveryAddress = rs.getString("deliveryaddress");
                    String deliveryPostcode = rs.getString("deliverypostcode");
                    LocalDate deliveryDate = rs.getDate("deliverydate").toLocalDate();


                    WorkOrderPlan workOrderPlan = new WorkOrderPlan(workId, orderId, driverId,
                            trailerLicencePlate, truckLicencePlate, trailerPickupLocation, amount, hazardousLiquid, trailerCleaned,
                            deliveryAddress, deliveryPostcode, deliveryDate);

                    workOrderPlans.add(workOrderPlan);

                    LOGGER.log(Level.INFO, "Found {0} in database", workOrderPlans);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return workOrderPlans;
    }

    @Override
    public Optional<Integer> save(WorkOrderPlan workOrderPlan) {
        String message = "The Customer to be added should not be null";
        WorkOrderPlan nonNullUser = Objects.requireNonNull(workOrderPlan, message);
        String sql = "INSERT INTO "
                + "workorderplan(orderid,driverid,trailerlicenceplate,trucklicenceplate,trailerpickuplocation," +
                "loadamount,hazardousliquid,trailercleaned,deliveryaddress," +
                "deliverypostcode,deliverydate)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        return connection.flatMap(conn -> {

            //why do we put the generated id in a optional?
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, nonNullUser.getOrderId());
                statement.setInt(2, nonNullUser.getDriverId());
                statement.setString(4, nonNullUser.getTrailerLicencePlate());
                statement.setString(5, nonNullUser.getTruckLicencePlate());
                statement.setString(6, nonNullUser.getTrailerPickupLocation());
                statement.setDouble(7, nonNullUser.getLoadAmount());
                statement.setBoolean(8, nonNullUser.isHazardousLiquid());
                statement.setBoolean(8, nonNullUser.isTrailerCleaned());
                statement.setString(9, nonNullUser.getDeliveryAddress());
                statement.setString(10, nonNullUser.getDeliveryPostcode());
                statement.setDate(11, Date.valueOf(nonNullUser.getDeliveryDate()));

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
                        //why do we put in array?
                        new Object[]{nonNullUser, numberOfInsertedRows > 0});

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }


    @Override
    public void update(WorkOrderPlan workOrderPlan) {
        String message = "The Customer to be updated should not be null";
        WorkOrderPlan nonNullUser = Objects.requireNonNull(workOrderPlan, message);
        WorkOrderPlan workOrderPlan1 = Objects.requireNonNull(workOrderPlan, message);
        String sql = "UPDATE workorderplan "
                + "SET "
                + "orderid = ?, "
                + "driverid = ?, "
                + "trailerlicenceplate = ?, "
                + "trucklicenceplate = ?, "
                + "trailerpickuplocation = ? "
                + "loadamount = ?, "
                + "hazardousliquid = ?, "
                + "trailercleaned = ?, "
                + "deliveryaddress = ?, "
                + "deliverypostcode = ?, "
                + "deliverydate = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {


                statement.setInt(1, nonNullUser.getOrderId());
                statement.setInt(2, nonNullUser.getDriverId());
                statement.setString(4, nonNullUser.getTrailerLicencePlate());
                statement.setString(5, nonNullUser.getTruckLicencePlate());
                statement.setString(6, nonNullUser.getTrailerPickupLocation());
                statement.setDouble(7, nonNullUser.getLoadAmount());
                statement.setBoolean(8, nonNullUser.isHazardousLiquid());
                statement.setBoolean(8, nonNullUser.isTrailerCleaned());
                statement.setString(9, nonNullUser.getDeliveryAddress());
                statement.setString(10, nonNullUser.getDeliveryPostcode());
                statement.setDate(11, Date.valueOf(nonNullUser.getDeliveryDate()));

                statement.setInt(12, nonNullUser.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Customer updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void delete(WorkOrderPlan workOrderPlan) {
        String message = "The customer to be deleted should not be null";
        WorkOrderPlan nonNullUser = Objects.requireNonNull(workOrderPlan, message);
        String sql = "DELETE FROM workOrderPlan WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the WorkOrderPlan deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}

