package com.imperium.imperium.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imperium.imperium.model.User;

public interface IUserService {

    void save(User u);

    void update(User u);

    User findByUsername(String username);

    ArrayList<User> findAll();

    User findById(Long id);

    void autologin(String username, String password);

    public void autologout(HttpServletRequest request, HttpServletResponse response);

}
