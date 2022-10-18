package com.imperium.imperium.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

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

    /**
     * @param request  : provide request information for HTTP servlets
     * @param response : provide HTTP-specific functionality in sending a response
     * @return String : return the index file
     */
    @GetMapping(value = { "/", "/index" })
    public String indexPage(HttpServletRequest request, HttpServletResponse response) {
        userService.autologout(request, response);

        return "index";
    }

    /**
     * @return String : redirect to the index page
     */
    @GetMapping(value = "/home/logout")
    private String logoutPage() {
        return "redirect:/";
    }

    /**
     * @return String : return the signin file
     */
    @GetMapping(value = "/signIn")
    private String signInPage() {
        return "authentification/signIn";
    }

    /**
     * @param model   : holder for model attributes
     * @param "error" : error (RequestParam)
     * @param error   : Boolean for error parameter
     * @return String : return the login file
     */
    @GetMapping(value = "/logIn")
    private String logInPage(Model model, @RequestParam(value = "error", defaultValue = "false") Boolean error) {
        if (error)
            model.addAttribute("error", "Username and password invalid.");

        return "authentification/logIn";
    }

    /**
     * @param model   : holder for model attributes
     * @param "error" : error (RequestParam)
     * @param error   : Boolean for error parameter
     * @return String : return the home file
     */
    @GetMapping(value = "/home")
    private String homePage(Model model, @RequestParam(value = "error", defaultValue = "no-error") String error) {

        String jobtitle = UserController.getCurrentUser().getJobtitle();
        Long id = UserController.getCurrentUser().getId();

        LocalDate currentDate = LocalDate.now();
        Month m = currentDate.getMonth();
        int dom = currentDate.getDayOfMonth();
        model.addAttribute("month", m);
        model.addAttribute("day", dom);

        // USER DATA
        model.addAttribute("username", UserController.getCurrentUser().getUsername());
        model.addAttribute("firstname", UserController.getCurrentUser().getFirstname());
        model.addAttribute("lastname", UserController.getCurrentUser().getLastname());
        model.addAttribute("jobtitle", jobtitle);

        // ! **** For Dev ****
        model.addAttribute("allUsers", userService.findAll());
        // ! *****************

        // USER PROJECTS DATA
        model.addAttribute("myProjects", projectService.findProjectByUserId(id));
        int nbr_projects = projectService.findProjectByUserId(id).size();
        model.addAttribute("nbrprojects", nbr_projects);
        model.addAttribute("sharedProjects",
                projectService.getArrayProjectByArrayidProject(
                        accessService.findIdProjectSharedWithUserId(UserController.getCurrentUser().getId())));

        if (projectService.findProjectByUserId(id).isEmpty())
            model.addAttribute("noProjects", "No project found !");

        // PROCESS ERROR MSG
        if (!error.equals("no-error"))
            model.addAttribute("error", "You Already have a project with the same name");

        return "home";
    }

    /**
     * @param model   : holder for model attributes
     * @param "id"    : Project id (RequestParam)
     * @param "error" : error (RequestParam)
     * @return String : return the project file
     */
    @GetMapping(value = { "/home/create-project", "/home/open-project", "/home/create-board", "/home/create-task" })
    private String openProject(Model model, @RequestParam(value = "id", defaultValue = "error") Long id,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long userId = UserController.getCurrentUser().getId();
        String projectName = projectService.findById(id).getName();

        // ! **** For Dev ****
        model.addAttribute("allUsers", userService.findAll());
        // ! *****************

        // USER DATA
        model.addAttribute("isOwner", (projectService.findProjectOwner(id).getId().equals(userId)));
        model.addAttribute("username", UserController.getCurrentUser().getUsername());

        // BOARD DATA
        model.addAttribute("boards", boardService.findBoardsByProjectId(id));
        model.addAttribute("tasks", taskService.findTaskByProjectId(id));

        // PROJECT DATA
        model.addAttribute("projectName", projectName);
        model.addAttribute("id", id);
        model.addAttribute("myProjects", projectService.findProjectByUserId(userId));

        ArrayList<User> l = userService.getArrayUserByArrayidUser(accessService.findIdContributorByIdProject(id));
        l.add(projectService.findProjectOwner(id));
        model.addAttribute("contributor", l);

        // PROCESS ERROR
        if (!error.equals("no-error"))
            model.addAttribute("error", "Error : Unable share the project !");

        return "project";
    }

    /**
     * @param model : holder for model attributes
     * @param u     : User object
     * @param error : error (RequestParam)
     * @return String : return the profile file
     */
    @GetMapping(value = "/home/profile")
    private String profile(Model model, User u,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {
        Long userId = UserController.getCurrentUser().getId();

        // USER DATA
        model.addAttribute("user", UserController.getCurrentUser());
        model.addAttribute("myProjects", projectService.findProjectByUserId(userId));

        // // USER IMAGE DATA
        // User uImage = UserController.getCurrentUser();
        // model.addAttribute("uImage", uImage);

        // PROCESS ERROR MSG
        if (error.equals("password")) {
            model.addAttribute("user", UserController.getCurrentUser());
            model.addAttribute("error", "Passwords are not matching or Wrong previous password!");
        } else if (error.equals("username")) {
            model.addAttribute("error", "Username already used.");
        }

        return "profile";
    }

}
