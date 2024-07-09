package Utilities;

import com.sun.net.httpserver.HttpExchange;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RequestUtil {

    public static JSONObject parseRequestBody(HttpExchange exchange) throws IOException, ParseException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder requestBody = new StringBuilder();
        int b;
        while ((b = br.read()) != -1) {
            requestBody.append((char) b);
        }
        br.close();
        isr.close();

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(requestBody.toString());
    }
}
