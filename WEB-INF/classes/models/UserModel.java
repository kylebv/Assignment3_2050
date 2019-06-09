package models;

/**
 * Created by kyleb on 8/06/2019.
 */
public class UserModel {
    String username, password, role;

    public UserModel(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
