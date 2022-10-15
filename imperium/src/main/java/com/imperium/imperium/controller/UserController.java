package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.user.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    private static User user;

    @PostMapping(value = "/signIn")
    public String signIn(Model model, User u) {
        String pwd = u.getPassword();

        if (service.isUserRegistered(u)) {
            model.addAttribute("error", "Username already used.");
            return "authentification/signIn";
        }

        service.save(u);
        setCurrentUser(u);

        service.autologin(u.getUsername(), pwd);

        return "redirect:/home";
    }

    @PostMapping(value = "/process-logIn")
    public String logIn(Model model, User u) {

        if (service.canConnect(u)) {
            setCurrentUser(service.findByUsername(u.getUsername()));
            return "redirect:/home";
        }

        return "authentification/logIn";
    }

    public static void setCurrentUser(User u) {
        user = u;
    }

    public static User getCurrentUser() {
        return user;
    }

}
