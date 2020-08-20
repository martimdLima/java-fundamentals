package com.martimlima.javaprojects.jdbc.src.view;

import com.martimlima.javaprojects.jdbc.src.controller.UserListController;
import com.martimlima.javaprojects.jdbc.src.model.User;

public class UserListView extends AbstractView {

    private UserListController controller;

    @Override
    public void show() {
        showUserList();
    }

    private void showUserList() {

        System.out.printf("\n%-12s %-30s", "Username", "Email");
        System.out.printf("\n%-50s", new String(new char[50]).replace("\0", "-"));

        for (User user : controller.getUserList()) {
            System.out.printf("\n%-12s %-30s", user.getUsername(), user.getEmail());
        }
    }


    public void setController(UserListController controller) {
        this.controller = controller;
    }
}
