import Handlers.UserAuthHandler;
import Repository.Database;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;

import Handlers.UserHandler;


public class Server {

    static Connection conn = Database.getConnection();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/users/auth", new UserAuthHandler(conn));
        server.createContext("/api/users", new UserHandler(conn));
        server.setExecutor(null);
        server.start();
        System.out.println("Server is up and running");
    }
}
