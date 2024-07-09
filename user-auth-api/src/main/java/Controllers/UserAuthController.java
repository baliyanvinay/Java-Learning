package Controllers;

import Services.UserAuthService;
import org.json.simple.JSONObject;

public class UserAuthController {
    public static JSONObject verifyCredentials(JSONObject payload, UserAuthService service) {
        JSONObject response = new JSONObject();
        if(service.verifyCredentials(payload)) {
            response.put("message", "User credentials verified.");
        } else {
            response.put("message", "Invalid credentials.");
        }
        return response;
    }
}
