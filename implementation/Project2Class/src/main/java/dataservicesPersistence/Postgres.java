/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
package dataservicesPersistence;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.*;


public class Postgres {
    private final String SERVER_NAME;
    private static final String DB_NAME = "postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "mypassword";

    private PGConnectionPoolDataSource source;


    public Postgres(String server_name) {
        SERVER_NAME = server_name;
        createPGDataSource();
    }


    protected Connection getConnection() throws SQLException {
        return source.getPooledConnection().getConnection();
    }

    private void createPGDataSource() {
        source = new PGConnectionPoolDataSource();
        source.setServerName(SERVER_NAME);
        source.setDatabaseName(DB_NAME);
        source.setUser(DB_USER);
        source.setPassword(DB_PWD);
    }


    protected PreparedStatement createPreparedStatementWithKeysReturned(String sql) throws SQLException {
        return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }


    protected ResultSet executeQuery(String sql) throws SQLException {
        return getConnection().createStatement().executeQuery(sql);
    }


}
*/