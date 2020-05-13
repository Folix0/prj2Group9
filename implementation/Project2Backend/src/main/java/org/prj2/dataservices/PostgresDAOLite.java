package org.prj2.dataservices;

import org.prj2.model.AccountantOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDAOLite extends Postgres implements DAOLite<AccountantOrder> {

    public static final String INSERT_ACCOUNTANTORDER_STATEMENT = "insert into accountantorder(customerid,amount,destinationaddress,destinationpostcode,pickupaddress,deliverydate,hazardous,email,totalprice) values(?,?,?,?,?,?,?,?,?)";

    public static final String SELECT_ACCOUNTANTORDER_STATEMENT = "select * from accountantorder where orderid=";

    public static final String SELECT_ALL_STATEMENT = "select * from accountantorder";

    public PostgresDAOLite(String server_name) {
        super(server_name);
    }

    @Override
    public AccountantOrder save(AccountantOrder accountantOrder) {
        try (PreparedStatement pst = createPreparedStatementWithKeysReturned(INSERT_ACCOUNTANTORDER_STATEMENT)) {
            pst.setInt(1, accountantOrder.getCustomerId());
            pst.setDouble(2, accountantOrder.getAmount());
            pst.setString(3, accountantOrder.getDestinationAddress());
            pst.setInt(4, accountantOrder.getDestinationPostcode());
            pst.setString(5, accountantOrder.getPickupAddress());
            pst.setDate(6, accountantOrder.getDeliveryDate());
            pst.setBoolean(7, accountantOrder.isHazardous());
            pst.setString(8, accountantOrder.getEmail());
            pst.setDouble(9, accountantOrder.getTotalPrice());

            pst.execute();
            ResultSet keyset = pst.getGeneratedKeys();
            if (keyset.next()) {
                accountantOrder.setOrderId(keyset.getInt(1));
            }

            return accountantOrder;
        } catch (SQLException ex) {
            Logger.getLogger(PostgresDAOLite.class.getName()).
                    log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public AccountantOrder get(int orderId) {
        // be aware that this is unsafe! Use prepared statements instead.
        try (ResultSet rs = executeQuery(SELECT_ACCOUNTANTORDER_STATEMENT + orderId)) {
            return createAccountantOrder(orderId, rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresDAOLite.class.getName()).
                    log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<AccountantOrder> getAll() {
        List<AccountantOrder> result = new ArrayList<>();
        try (ResultSet rs = executeQuery(SELECT_ALL_STATEMENT)) {
            getAccountantOrderFromResult(result, rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresDAOLite.class.getName()).
                    log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
        return result;
    }

    private List<AccountantOrder> getAccountantOrderFromResult(List<AccountantOrder> result, ResultSet rs) throws SQLException {

        List<AccountantOrder> accountantorders = new ArrayList<>();
        while (rs.next()) {
            int orderId = rs.getInt("orderid");

            int customerId = rs.getInt("customerid");
            double amount = rs.getDouble("amount"); //column name
            String destinationAddress = rs.getString("destinationaddress");
            int destinationPostcode = rs.getInt("destinationpostcode");
            String pickupAddress = rs.getString("pickupaddress");
            Date deliveryDate = rs.getDate("deliverydate");
            boolean hazardous = rs.getBoolean("hazardous");
            String email = rs.getString("email");
            double totalPrice = rs.getDouble("totalprice");
            AccountantOrder accountantOrder = new AccountantOrder(orderId, customerId, amount, destinationAddress, destinationPostcode, pickupAddress, deliveryDate, hazardous, email, totalPrice);
            accountantorders.add(accountantOrder);

        }

        return accountantorders;
    }

    @Override
    public AccountantOrder update(AccountantOrder p) {
        return null; // exercise for the student
    }

    @Override
    public void delete(int orderId) {
        String sql = "delete from accountantorder where orderid = ?";
        try (
                Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, orderId);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresDAOLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private AccountantOrder createAccountantOrder(int orderId, ResultSet rs) throws SQLException {
        if (rs.next()) {


            int customerId = rs.getInt("customerid");
            double amount = rs.getDouble("amount"); //column name
            String destinationAddress = rs.getString("destinationaddress");
            int destinationPostcode = rs.getInt("destinationpostcode");
            String pickupAddress = rs.getString("pickupaddress");
            java.sql.Date deliveryDateSql = rs.getDate("delilverydate");
            boolean hazardous = rs.getBoolean("hazardous");
            String email = rs.getString("email");
            double totalPrice = rs.getDouble("totalprice");


            // return new AccountantOrder(id, name, birth_year);
            return new AccountantOrder(customerId, amount, destinationAddress,
                    destinationPostcode, pickupAddress, deliveryDateSql, hazardous, email, totalPrice);

        } else {
            return null;
        }
    }
}


