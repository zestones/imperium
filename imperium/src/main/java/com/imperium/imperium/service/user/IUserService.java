package com.imperium.imperium.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imperium.imperium.model.User;

public interface IUserService {

/* method save user password into user repository */
    void save(User u);
/* method update, call save method from Crudrepository, save user infos */
    void update(User u);
/* method that user CrudRepository method findbyId return user Id */
    User findById(Long id);

    ArrayList<User> findAll();

    User findByUsername(String username);

    void autologin(String username, String password);
/* method for auto log-out check the authentication is needed */
    void autologout(HttpServletRequest request, HttpServletResponse response);

}
