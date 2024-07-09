package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;

import Controllers.UserAuthController;
import Services.UserAuthService;
import Repository.UserRepository;
import Utilities.RequestUtil;
import Utilities.ResponseUtil;


public class UserAuthHandler implements HttpHandler {
    Connection conn;
    UserRepository repository;
    UserAuthService service;

    public UserAuthHandler(Connection conn) {
        this.conn = conn;
        this.repository = new UserRepository(this.conn);
        this.service = new UserAuthService(repository);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();

        if ("POST".equals(requestMethod)) {
            JSONObject parsedRequestBody = null;
            JSONObject response;
            try {
                parsedRequestBody = RequestUtil.parseRequestBody(exchange);
                response = UserAuthController.verifyCredentials(parsedRequestBody, this.service);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ResponseUtil.jsonResponse(exchange, response);
        }
    }
}
