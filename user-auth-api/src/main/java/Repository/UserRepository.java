package Repository;

import java.sql.*;
import java.util.ArrayList;

import Controllers.UserController;
import Models.UserModel;


public class UserRepository {
    Connection conn;

    String createTableStmt = """
            CREATE TABLE users (
                 userId INTEGER PRIMARY KEY AUTOINCREMENT,
                 username VARCHAR(255) NOT NULL,
                 hashedPassword VARCHAR(2056) NOT NULL,
                 salt VARCHAR(2056) NOT NULL
            );
            """;
    String addRowStmt = """
            INSERT INTO users (username, hashedPassword, salt)
            VALUES (\"%s\", \"%s\", \"%s\");
            """;
    String getAllRowsStmt = """
            SELECT * FROM users;
            """;
    String getUserByUsernameStmt = """
            SELECT * FROM users WHERE username = \"%s\";
            """;

    public UserRepository(Connection conn) {
        this.conn = conn;
        if(!isTableCreated()) {
            createTable();
        }
    }

    private boolean isTableCreated() {
        try {
            DatabaseMetaData dbMetaData = this.conn.getMetaData();
            ResultSet tables = dbMetaData.getTables(null, null, "users", null);
            if(tables.next()) {
                System.out.println("Database table users exits.");
                return true;
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return false;
    }

    private void createTable() {
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(createTableStmt);
            stmt.close();
            System.out.println("Table created in database.");
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public boolean addUser(UserModel user) {
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(String.format(addRowStmt, user.username, user.hashedPassword, user.salt));
            stmt.close();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return false;
    }

    public ArrayList<UserModel> getUsers() {
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(getAllRowsStmt);
            ArrayList<UserModel> userList = new ArrayList<UserModel>();

            while(resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                UserModel user = new UserModel(userId, username);
                userList.add(user);
            }

            return userList;

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return null;
    }

    public UserModel getUserByUsername(String username) {
        try {
            Statement stmt = this.conn.createStatement();
            stmt.setMaxRows(1);
            ResultSet resultSet = stmt.executeQuery(String.format(getUserByUsernameStmt, username));

            int userId = resultSet.getInt("userId");
            String hashedPassword = resultSet.getString("hashedPassword");
            String salt = resultSet.getString("salt");

            return new UserModel(userId, username, hashedPassword, salt);

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return null;
    }
}
