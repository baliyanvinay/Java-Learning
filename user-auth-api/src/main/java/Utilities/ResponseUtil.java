package Utilities;

import com.sun.net.httpserver.HttpExchange;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

public class ResponseUtil {

    public static void jsonResponse(HttpExchange exchange, JSONObject response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.toString().getBytes().length);
        exchange.getResponseBody().write(response.toString().getBytes());
        exchange.getResponseBody().close();
    }

    public static void jsonResponse(HttpExchange exchange, List<JSONObject> response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.toString().getBytes().length);
        exchange.getResponseBody().write(response.toString().getBytes());
        exchange.getResponseBody().close();
    }

}
