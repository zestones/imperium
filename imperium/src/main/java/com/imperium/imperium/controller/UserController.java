package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(value = "/signIn")
    public String signIn(Model model, User u) {
        if (service.isUserRegistered(u)) {
            model.addAttribute("error", "Username already used.");
            return "authentification/signIn";
        }

        service.save(u);
        return "redirect:/home?username=" + u.getUsername();
    }

    @PostMapping(value = "/logIn")
    public String logIn(Model model, User u) {

        if (service.canConnect(u))
            return "redirect:/home?username=" + u.getUsername();

        model.addAttribute("error", "Username and password invalid.");
        return "authentification/logIn";
    }

}
