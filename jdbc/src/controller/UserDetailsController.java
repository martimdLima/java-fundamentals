package com.martimlima.javaprojects.jdbc.src.controller;

import com.martimlima.javaprojects.jdbc.src.model.User;

public class UserDetailsController extends AbstractController {


    public User getUser(String username) {
        return userService.findByName(username);
    }
}
