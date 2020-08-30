package com.martimlima.javaprojects.jdbc.src.controller;


import com.martimlima.javaprojects.jdbc.src.model.User;

import java.util.List;

public class UserListController extends AbstractController {

    public List<User> getUserList() {
        return userService.findAll();
    }

}
