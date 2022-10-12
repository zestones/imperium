package com.imperium.imperium.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imperium.imperium.model.User;

public interface IUserService {
    // @Modifying
    // @Query("update User u set u.firstname=?1, u.lastname = ?2 where u.id = ?3")
    // void setUserInfoById(String firstname, String lastname, Long userId);

    void save(User u);

    void save_bis(User u);

    User update(User u, Long id);

    User findByUsername(String username);

    ArrayList<User> findAll();

    User findById(Long id);

    void autologin(String username, String password);

    public void autologout(HttpServletRequest request, HttpServletResponse response);

}
