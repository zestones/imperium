package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

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

    @GetMapping(value = "/create-project")
    private String createProject(Model model, @RequestParam(value = "name", defaultValue = "error") String name) {
        model.addAttribute("name", name);
        model.addAttribute("username", UserController.getUser().getUsername());

        return "project";
    }

    @GetMapping(value = "/home")
    private String homePage(Model model, @RequestParam(value = "username", defaultValue = "error") String username,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        model.addAttribute("username", username);
        model.addAttribute("allUsers", userService.findAll());

        model.addAttribute("projects", projectService.findProjectByUserId(UserController.getUser().getId()));

        if (!error.equals("no-error"))
            model.addAttribute("error", "You Already have a project with the same name");

        return "home";
    }
}
