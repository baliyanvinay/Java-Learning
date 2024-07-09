package Models;

import Utilities.ArrayUtil;
import Utilities.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserModel {
    public int userId;
    public String username;
    private String password;
    public String hashedPassword;
    public String salt;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserModel(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UserModel(int userId, String username, String hashedPassword, String salt) {
        this.userId = userId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public String toString() {
        return this.username;
    }

    public void setPassword() {
        byte[] saltInBytes = StringUtil.getSalt();
        setPassword(saltInBytes);
    }

    public void setPassword(byte[] saltInBytes) {
        byte[] passwordInBytes = this.password.getBytes();
        byte[] saltedPassword = ArrayUtil.concatenateByteArray(passwordInBytes, saltInBytes);

        // hash the password with SHA-256
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPasswordInBytes = md.digest(saltedPassword);
            this.hashedPassword = StringUtil.encode(hashedPasswordInBytes);
            this.salt = StringUtil.encode(saltInBytes);
        } catch (NoSuchAlgorithmException error) {
            System.out.println(error.getMessage());
        }
    }

}