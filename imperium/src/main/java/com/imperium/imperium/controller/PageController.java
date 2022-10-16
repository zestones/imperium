package com.imperium.imperium.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.access.AccessService;
import com.imperium.imperium.service.board.BoardService;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.task.TaskService;
import com.imperium.imperium.service.user.UserService;


/* Main Controller class, most of the getmapping get here */
@Controller
public class PageController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    AccessService accessService;

    @Autowired
    BoardService boardService;

    @Autowired
    TaskService taskService;
/* method for auto log-out when user is into index page call userService class method */
    @GetMapping(value = { "/", "/index" })
    public String indexPage(HttpServletRequest request, HttpServletResponse response) {
        userService.autologout(request, response);

        return "index";
    }
/* method for after PostMapping method, redirect into index html page after click into log-out bouton */
    @GetMapping(value = "/home/logout")
    private String logoutPage() {
        return "redirect:/";
    }
/* method GET redirect into authentication/signIn  */
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
/* method GET for home page get the user configuration, project name and redirect into home html page */
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

    @GetMapping(value = { "/home/create-project", "/home/open-project", "/home/create-board", "/home/create-task" })
    private String openProject(Model model, @RequestParam(value = "id", defaultValue = "error") Long id,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long userId = UserController.getCurrentUser().getId();
        String projectName = projectService.findById((Long) id).getName();

        // USER DATA
        model.addAttribute("isOwner", (projectService.getProjectOwner(id).getId().equals(userId)));
        model.addAttribute("username", UserController.getCurrentUser().getUsername());

        // BOARD DATA
        model.addAttribute("boards", boardService.findBoardsByProjectId(id));
        model.addAttribute("tasks", taskService.findTaskByProjectId(id));

        // PROJECT DATA
        model.addAttribute("projectName", projectName);
        model.addAttribute("id", id);
        model.addAttribute("myProjects", projectService.findProjectByUserId(userId));
        model.addAttribute("access",
                userService.getArrayUserByArrayidUser(
                        accessService.findIdContributorByIdProject(id)));

        // PROCESS ERROR
        if (!error.equals("no-error"))
            model.addAttribute("error", "Error : Unable share the project !");

        return "project";
    }
/* method GET for profil page get user details */
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
