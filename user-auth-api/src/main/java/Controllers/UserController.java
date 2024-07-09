package Controllers;

import Models.UserModel;
import Services.UserService;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static JSONObject postUser(JSONObject payload, UserService service) {
        JSONObject response = new JSONObject();
        if(service.addUser(payload)) {
            response.put("message", "User added successfully.");
        } else {
            response.put("message", "Error in adding user.");
        }
        return response;
    }

    public static List<JSONObject> getUsers(UserService service) {
        ArrayList<UserModel> userModelArrayList =  service.getAllUsers();
        List<JSONObject> response = new ArrayList<>();
        for(UserModel user : userModelArrayList) {
            JSONObject obj = new JSONObject();
            obj.put("userId", user.userId);
            obj.put("username", user.username);
            response.add(obj);
        }
        return response;
    }
}
