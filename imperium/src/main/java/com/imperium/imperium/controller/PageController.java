package com.imperium.imperium.controller;

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
        return "authentification/logIn";
    }

    @GetMapping(value = "/home")
    private String homePage(Model model, @RequestParam(value = "username", defaultValue = "error") String username,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        model.addAttribute("username", username);
        model.addAttribute("allUsers", userService.findAll());

        model.addAttribute("myProjects", projectService.findProjectByUserId(UserController.getUser().getId()));
        model.addAttribute("sharedProjects",
                projectService.getArrayProjectByArrayidProject(
                        accessService.findIdProjectSharedWithUserId(UserController.getUser().getId())));

        if (!error.equals("no-error"))
            model.addAttribute("error", "You Already have a project with the same name");

        return "home";
    }

    @GetMapping(value = { "/create-project", "/open-project" })
    private String openProject(Model model, @RequestParam(value = "id", defaultValue = "error") Long id,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long userId = UserController.getUser().getId();
        final String name = projectService.findById((Long) id).getName();

        model.addAttribute("username", UserController.getUser().getUsername());

        model.addAttribute("name", name);
        model.addAttribute("id", id);

        model.addAttribute("myProjects", projectService.findProjectByUserId(userId));

        model.addAttribute("access",
                userService.getArrayUserByArrayidUser(
                        accessService.findIdContributorByIdProject(id)));

        if (!error.equals("no-error"))
            model.addAttribute("error", "Username not found !");

        return "project";
    }
}
