package com.imperium.imperium.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.access.AccessService;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    AccessService accessService;

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
    private String homePage(Model model, @RequestParam(value = "username", defaultValue = "error") String username,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        model.addAttribute("username", username);
        model.addAttribute("allUsers", userService.findAll());

        model.addAttribute("projects", projectService.findProjectByUserId(UserController.getUser().getId()));

        if (!error.equals("no-error"))
            model.addAttribute("error", "You Already have a project with the same name");

        return "home";
    }

    @GetMapping(value = { "/create-project", "/open-project" })
    private String openProject(Model model, @RequestParam(value = "name", defaultValue = "error") String name,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long userId = UserController.getUser().getId();
        Long projectId = projectService.findProjectByUserIdAndName(userId, name).getId();

        model.addAttribute("username", UserController.getUser().getUsername());

        model.addAttribute("name", name);
        model.addAttribute("id", projectId);

        model.addAttribute("projects", projectService.findProjectByUserId(userId));

        // TODO : display object users - create in service method to get
        ArrayList<Long> list = accessService.findIdContributorByIdProject(projectId);

        model.addAttribute("access", list);

        if (!error.equals("no-error"))
            model.addAttribute("error", "Username not found !");

        return "project";
    }
}
