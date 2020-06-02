package dataservicesPersistence;

import connection.JdbcConnection;
import entities.DeliveryTour;
import entities.Driver;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryTourDAO implements DAOlite<DeliveryTour, Integer> {
    private static final Logger LOGGER = Logger.getLogger(DeliveryTourDAO.class.getName());
    private final Optional<Connection> connection;

    public DeliveryTourDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<DeliveryTour> get(int id) {
        return connection.flatMap(conn -> {
            Optional<DeliveryTour> deliveryTour = Optional.empty();
            String sql = "SELECT * FROM deliverytour WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                if (rs.next()) {

                    int deliveryId = rs.getInt("id");
                    LocalDate start = rs.getDate("startofdeliverydate").toLocalDate();
                    LocalDate finish = rs.getDate("finishofdeliverydate").toLocalDate();



                    deliveryTour = Optional.of(new DeliveryTour(deliveryId,start,finish));
                    LOGGER.log(Level.INFO, "Found {0} in database", deliveryTour.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return deliveryTour;
        });
    }


    @Override
    public Collection<DeliveryTour> getAll() {
        List<DeliveryTour> deliveryTours = new ArrayList<>();
        String sql = "SELECT * FROM deliverytour";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {
                    int deliveryId = rs.getInt("id");
                    LocalDate start = rs.getDate("startofdeliverydate").toLocalDate();
                    LocalDate finish = rs.getDate("finishofdeliverydate").toLocalDate();


                    DeliveryTour deliveryTour = new DeliveryTour(deliveryId, start,finish);

                    deliveryTours.add(deliveryTour);

                    LOGGER.log(Level.INFO, "Found {0} in database", deliveryTours);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return deliveryTours;
    }

    @Override
    public Optional<Integer> save(DeliveryTour deliveryTour) {
        String message = "The Delivery Tour to be added should not be null";
        DeliveryTour nonNullUser = Objects.requireNonNull(deliveryTour, message);
        String sql = "INSERT INTO "
                + "deliverytour(startofdeliverydate,finishofdeliverydate)"
                + "VALUES(?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setDate(1, Date.valueOf(nonNullUser.getStartOfDeliveryDate()));
                statement.setDate(2, Date.valueOf(nonNullUser.getFinishOfDeliveryDate()));



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
    public void update(DeliveryTour deliveryTour) {
        String message = "The Delivery Tour to be updated should not be null";
        DeliveryTour nonNullUser = Objects.requireNonNull(deliveryTour, message);
        String sql = "UPDATE deliverytour "
                + "SET "
                + "startofdeliverydate = ?, "
                + "finishofdeliverydate = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setDate(1, Date.valueOf(nonNullUser.getStartOfDeliveryDate()));
                statement.setDate(2, Date.valueOf(nonNullUser.getFinishOfDeliveryDate()));

                statement.setInt(3, nonNullUser.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Delivery Tour updated successfully? {0}",
                        numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(DeliveryTour deliveryTour) {

        String message = "The Delivery Tour to be deleted should not be null";
        DeliveryTour nonNullUser = Objects.requireNonNull(deliveryTour, message);
        String sql = "DELETE FROM deliverytour WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullUser.getId());

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the Delivery Tour deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}

