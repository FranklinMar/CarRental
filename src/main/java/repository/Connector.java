package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connection connector;

    public static Connection getConnection(String URL) throws SQLException {
        if (connector == null){
            connector = DriverManager.getConnection(URL);
        } else {
            throw new SQLException("Connection already exists");
        }
        return connector;
    }

    public static void releaseConnection() throws SQLException {
        connector.close();
        connector = null;
    }
}
