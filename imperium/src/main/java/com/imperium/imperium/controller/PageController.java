package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.UserService;

@Controller
public class PageController {

    @Autowired
    UserService service;

    @GetMapping(value = { "/", "/index" })
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/signIn")
    private String signInPage() {
        return "authentification/signIn";
    }

    @GetMapping(value = "/logIn")
    private String logInPage(Model model) {

        model.addAttribute("userForm", new User());

        return "authentification/logIn";
    }

    @GetMapping(value = "/home")
    private String homePage(Model model, @RequestParam(value = "username", defaultValue = "error") String username) {
        model.addAttribute("username", username);
        model.addAttribute("allUsers", service.findAll());
        return "home";
    }
}
