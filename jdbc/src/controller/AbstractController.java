package com.martimlima.javaprojects.jdbc.src.controller;


import com.martimlima.javaprojects.jdbc.src.service.UserService;
import com.martimlima.javaprojects.jdbc.src.view.View;

public abstract class AbstractController implements Controller {

    protected UserService userService;
    protected View view;

    @Override
    public void init() {
        view.show();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setView(View view) {
        this.view = view;
    }
}
