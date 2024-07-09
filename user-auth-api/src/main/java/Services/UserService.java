package Services;

import Models.UserModel;
import Repository.UserRepository;
import org.json.simple.JSONObject;

import java.util.ArrayList;


public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean addUser(JSONObject userObject) {
        UserModel user = new UserModel(userObject.get("username").toString(), userObject.get("password").toString());
        user.setPassword();
        boolean status = this.repository.addUser(user);
        System.out.println("User add status: " + status);
        return status;
    }

    public ArrayList<UserModel> getAllUsers() {
        return this.repository.getUsers();
    }

    public void getUserById() {

    }
}
