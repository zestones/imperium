package com.imperium.imperium.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imperium.imperium.model.User;

public interface IUserService {

    void save(User u);

    void update(User u);

    User findById(Long id);

    ArrayList<User> findAll();

    User findByUsername(String username);

    void autologin(String username, String password);

    void autologout(HttpServletRequest request, HttpServletResponse response);

}
