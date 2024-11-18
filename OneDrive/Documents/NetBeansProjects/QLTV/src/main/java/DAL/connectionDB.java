package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {
    
    public static Connection openConnection() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTVOOAD;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String pass = "27032004";
        Connection connection;
            try {
                connection = DriverManager.getConnection(connectionUrl, user, pass);
            } catch (SQLException e) {
                throw new SQLException("Failed to connect to the database.");
            }
        return connection;
    }

    public static void closeConnection() {
        Connection connection = null ;
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void main(String[] args) throws SQLException {
        openConnection();
    }
}
