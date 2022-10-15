package com.imperium.imperium.service.user;

import java.util.ArrayList;

import com.imperium.imperium.model.User;

public interface IUserService {

    void save(User u);

    User findByUsername(String username);

    ArrayList<User> findAll();

    User findById(Long id);

    void autologin(String username, String password);
}
