package com.jxufe.halu.model;

import java.util.List;

public class User {
    private  String userID;
    private  String username;
    private  String password;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String userID, String username, String password) {

        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
