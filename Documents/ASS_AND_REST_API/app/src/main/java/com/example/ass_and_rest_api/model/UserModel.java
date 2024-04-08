package com.example.ass_and_rest_api.model;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("_id")
    private String id;
    private String email, username, password;
    private boolean available;

    public UserModel(String id, String email, String username, String password, boolean available) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.available = available;
    }

    public UserModel(String email, String username, String password, boolean available) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.available = available;
    }
    public UserModel( String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
