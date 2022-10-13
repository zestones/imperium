package com.imperium.imperium.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.ProjectList.ProjectListService;
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

    @Autowired
    ProjectListService projectListService;

    @GetMapping(value = { "/", "/index" })
    public String indexPage(HttpServletRequest request, HttpServletResponse response) {
        userService.autologout(request, response);

        return "index";
    }

    @GetMapping(value = "/home/logout")
    private String logoutPage() {
        return "redirect:/";
    }

    @GetMapping(value = "/signIn")
    private String signInPage() {
        return "authentification/signIn";
    }

    @GetMapping(value = "/logIn")
    private String logInPage(Model model, @RequestParam(value = "error", defaultValue = "false") Boolean error) {
        if (error)
            model.addAttribute("error", "Username and password invalid.");

        return "authentification/logIn";
    }

    @GetMapping(value = "/home")
    private String homePage(Model model, @RequestParam(value = "error", defaultValue = "no-error") String error) {

        model.addAttribute("username", UserController.getCurrentUser().getUsername());
        model.addAttribute("allUsers", userService.findAll());

        model.addAttribute("myProjects", projectService.findProjectByUserId(UserController.getCurrentUser().getId()));
        model.addAttribute("sharedProjects",
                projectService.getArrayProjectByArrayidProject(
                        accessService.findIdProjectSharedWithUserId(UserController.getCurrentUser().getId())));

        if (!error.equals("no-error"))
            model.addAttribute("error", "You Already have a project with the same name");

        return "home";
    }

    @GetMapping(value = { "/home/create-project", "/home/open-project" })
    private String openProject(Model model, @RequestParam(value = "id", defaultValue = "error") Long id,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long userId = UserController.getCurrentUser().getId();
        final String name = projectService.findById((Long) id).getName();

        model.addAttribute("isOwner", (projectService.getProjectOwner(id).getId().equals(userId)));

        model.addAttribute("username", UserController.getCurrentUser().getUsername());

        // List des List of Projects
        model.addAttribute("listOfProjectList", projectListService.findProjectListByProjectId(id));

        model.addAttribute("name", name);

        model.addAttribute("id", id);

        model.addAttribute("myProjects", projectService.findProjectByUserId(userId));

        model.addAttribute("access",
                userService.getArrayUserByArrayidUser(
                        accessService.findIdContributorByIdProject(id)));

        if (!error.equals("no-error"))
            model.addAttribute("error", "Error : Unable share the project !");

        return "project";
    }

    @GetMapping(value = "/home/profile")
    private String profile(Model model, User u,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {
        Long userId = UserController.getCurrentUser().getId();

        model.addAttribute("user", UserController.getCurrentUser());
        model.addAttribute("myProjects", projectService.findProjectByUserId(userId));

        if (error.equals("password")) {
            model.addAttribute("user", UserController.getCurrentUser());
            model.addAttribute("error", "Passwords are not matching or Wrong previous password!");
        } else if (error.equals("username")) {
            model.addAttribute("error", "Username already used.");
        }
        return "profile";
    }
}