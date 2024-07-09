package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Controllers.UserController;
import Repository.UserRepository;
import Services.UserService;
import Utilities.RequestUtil;
import Utilities.ResponseUtil;


public class UserHandler implements HttpHandler {
    Connection conn;
    UserRepository repository;
    UserService service;

    public UserHandler(Connection conn) {
        this.conn = conn;
        this.repository = new UserRepository(this.conn);
        this.service = new UserService(repository);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String requestMethod = exchange.getRequestMethod();

        if ("GET".equals(requestMethod)) {
            List<JSONObject> response = UserController.getUsers(this.service);
            ResponseUtil.jsonResponse(exchange, response);
        }
        else if ("POST".equals(requestMethod)) {
            JSONObject parsedRequestBody = null;
            JSONObject response;
            try {
                parsedRequestBody = RequestUtil.parseRequestBody(exchange);
                response = UserController.postUser(parsedRequestBody, this.service);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            ResponseUtil.jsonResponse(exchange, response);
        }
    }
}
