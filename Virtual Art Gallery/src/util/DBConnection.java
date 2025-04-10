package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String connStr = PropertyUtil.getPropertyString("db.properties");

                if (connStr == null) {
                    throw new RuntimeException("Database connection string is null. Check your properties file.");
                }
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
