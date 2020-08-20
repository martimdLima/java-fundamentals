package com.martimlima.javaprojects.jdbc.src;


import com.martimlima.javaprojects.jdbc.src.controller.LoginController;
import com.martimlima.javaprojects.jdbc.src.controller.MainController;
import com.martimlima.javaprojects.jdbc.src.controller.UserDetailsController;
import com.martimlima.javaprojects.jdbc.src.controller.UserListController;
import com.martimlima.javaprojects.jdbc.src.model.User;
import com.martimlima.javaprojects.jdbc.src.persistence.ConnectionManager;
import com.martimlima.javaprojects.jdbc.src.service.JdbcUserService;
import com.martimlima.javaprojects.jdbc.src.service.UserService;
import com.martimlima.javaprojects.jdbc.src.utils.Security;
import com.martimlima.javaprojects.jdbc.src.view.LoginView;
import com.martimlima.javaprojects.jdbc.src.view.MainView;
import com.martimlima.javaprojects.jdbc.src.view.UserDetailsView;
import com.martimlima.javaprojects.jdbc.src.view.UserListView;
import org.academiadecodigo.bootcamp.Prompt;

public class App {

    public static void main(String[] args) {

        LoginController loginController = new LoginController();
        MainController mainController = new MainController();
        UserListController userListController = new UserListController();
        LoginView loginView = new LoginView();
        MainView mainView = new MainView();
        UserListView userListView = new UserListView();
        UserDetailsController userDetailsController = new UserDetailsController();
        UserDetailsView userDetailsView = new UserDetailsView();
        Prompt prompt = new Prompt(System.in, System.out);

        ConnectionManager connectionManager = new ConnectionManager();

        UserService userService = new JdbcUserService(connectionManager);
        userService.add(new User("user1", "user1@mail.com", Security.getHash("secret"),"user1firstName", "user1lastName", "912345678"));
        userService.add(new User("user2", "user2@mail.com", Security.getHash("secret"),"user2firstName", "user2lastName", "963120215"));
        userService.add(new User("user3", "user3@mail.com", Security.getHash("secret"),"user3firstName", "user3lastName", "936501578"));
        userService.add(new User("user4", "user4@mail.com", Security.getHash("secret"),"user4firstName", "user4lastName", "923648204"));

        // Wire login controller and view
        loginView.setPrompt(prompt);
        loginView.setController(loginController);
        loginController.setUserService(userService);
        loginController.setView(loginView);
        loginController.setNextController(mainController);

        // Wire main controller and view
        mainView.setPrompt(prompt);
        mainView.setController(mainController);
        mainController.setView(mainView);
        mainController.setUserListController(userListController);
        mainController.setUserDetailsController(userDetailsController);

        // Wire userList controller and view
        userListView.setPrompt(prompt);
        userListView.setController(userListController);
        userListController.setUserService(userService);
        userListController.setView(userListView);

        // Wire userDetails controller and view
        userDetailsView.setPrompt(prompt);
        userDetailsView.setController(userDetailsController);
        userDetailsController.setUserService(userService);
        userDetailsController.setView(userDetailsView);


        // Start APP
        loginController.init();

        // Close DB connection
        connectionManager.close();

    }
}
