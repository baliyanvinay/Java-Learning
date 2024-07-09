package Services;

import Models.UserModel;
import Repository.UserRepository;
import Utilities.StringUtil;
import org.json.simple.JSONObject;

public class UserAuthService {

    private UserRepository repository;

    public UserAuthService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean verifyCredentials(JSONObject userObject) {
        UserModel userInDB = this.repository.getUserByUsername(userObject.get("username").toString());
        byte[] saltInBytes = StringUtil.decodeInBytes(userInDB.salt);

        UserModel user = new UserModel(userObject.get("username").toString(), userObject.get("password").toString());
        user.setPassword(saltInBytes);

        return user.hashedPassword.equals(userInDB.hashedPassword);
    }
}
