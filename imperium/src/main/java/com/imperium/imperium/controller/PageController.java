package com.imperium.imperium.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(value = { "/", "/index" })
    public String indexPage() {
        return "index";
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

    @GetMapping(value = "/home/logout")
    private String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);

        return "redirect:/index";
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
}
