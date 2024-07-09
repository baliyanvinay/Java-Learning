package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    private static Connection conn;
    final private static String DATABASE_URI = "jdbc:sqlite:application.db";

    public static Connection getConnection() {
        try {
            if(conn == null || conn.isClosed()) {
                conn = createConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    private static Connection createConnection() {
        try{
            var conn = DriverManager.getConnection(DATABASE_URI);
            System.out.println("Database Connection Created");
            return conn;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return null;
    }
}
