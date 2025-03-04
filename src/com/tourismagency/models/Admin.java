package com.tourismagency.models;

import com.tourismagency.exceptions.InvalidLoginException;

public class Admin {
    private static Admin instance;
    private String username;
    private String password;

    private Admin() {
        username = "admin";
        password = "admin123";
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void login(String username, String password) throws InvalidLoginException {
        if (!this.username.equals(username) || !this.password.equals(password)) {
            throw new InvalidLoginException("Invalid username or password.");
        }
    }
}
