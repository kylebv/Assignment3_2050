/* System component model
Seng2050 -  Web Engineering (Assignment 3)
Created by: Kyle Vincent
Modified by : Angus Simmons, Jeff Layton, Kyle Vincent, Sam Williams
Last edit: 9/6/19 */
package models;
/////////////////////////////USER DEFINED CLASS///////////////////////
public class UserModel {
/////////////////////////////////DECLARATIONS///////////////////////////
	String username, password, role;
    public UserModel(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public UserModel() {
    }
/////////////////////////////////GETTERS///////////////////////////////////
    public String getUsername() {
        return username;
    }
	 public String getPassword() {
        return password;
    }
	 public String getRole() {
        return role;
    }
/////////////////////////////////SETTERS///////////////////////////////////	
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
}