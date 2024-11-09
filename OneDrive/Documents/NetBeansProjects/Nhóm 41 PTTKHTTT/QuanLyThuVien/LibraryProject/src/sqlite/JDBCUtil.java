/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class JDBCUtil {
    
/*    public static void main(String args[]) {
       try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          String url = "jdbc:sqlserver://DESKTOP-EAAUMFL\\LAPTOP1:1433;databaseName=QLTV2;encrypt=true;trustServerCertificate=true";
           String user = "sa";
           String passwd = "123456";
          Connection c = DriverManager.getConnection(url, user, passwd);
            System.out.println("Kết nối thành công");
       } catch (Exception e) {
           e.printStackTrace();
        }
   }
}
*/ public static Connection getConnection() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=qltv;encrypt=true;trustServerCertificate=true";
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

    public static void closeConnection(Connection connection) {
    try {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection closed");
        }
    } catch (SQLException ex) {
        System.out.println("Error closing connection: " + ex.getMessage());
    }
}

    
}
