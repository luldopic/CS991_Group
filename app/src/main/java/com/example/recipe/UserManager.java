package com.example.recipe;

import com.example.recipe.model.User;

import java.util.ArrayList;

public class UserManager {

    private ArrayList<User> existingUsers;
    private ArrayList<User> loggedInUsers; // no actual use for this yet

    public UserManager() {
        this.existingUsers = new ArrayList<User>();
        this.loggedInUsers = new ArrayList<User>();
    }

    public void addUser(User user) {
        existingUsers.add(user);
    }

    public void removeUser(User user) {
        existingUsers.remove(user);
    }

    public ArrayList<User> getExistingUsers() {
        return existingUsers;
    }

    public void setExistingUsers(ArrayList<User> users) {
        this.existingUsers = users;
    }

    public void loginUser(User user) {
        for (User existingUser : existingUsers) {
            if (existingUser.getUsername().equals(user.getUsername()) && existingUser.getPassword().equals(user.getPassword())) {
                loggedInUsers.add(existingUser);
                System.out.println("Login Successful!");

            } else {
                System.out.println("Invalid username or password");
            }
        }
    }

    public void logoutUser(User user) {
        loggedInUsers.remove(user);
        System.out.println("Logged out");
    }

}

