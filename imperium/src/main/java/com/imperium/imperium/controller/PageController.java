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

<<<<<<< imperium/src/main/java/com/imperium/imperium/controller/PageController.java
import com.imperium.imperium.service.ProjectList.ProjectListService;
=======
import com.imperium.imperium.model.User;
>>>>>>> imperium/src/main/java/com/imperium/imperium/controller/PageController.java
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
<<<<<<< imperium/src/main/java/com/imperium/imperium/controller/PageController.java
    ProjectListService projectListService;

    @Autowired
    TaskService taskService;
=======
    BoardService projectListService;
>>>>>>> imperium/src/main/java/com/imperium/imperium/controller/PageController.java

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

    @GetMapping(value = "/home/logout")
    private String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);

        return "redirect:/index";
    }

    @GetMapping(value = "/home")
    private String homePage(Model model, @RequestParam(value = "error", defaultValue = "no-error") String error) {

<<<<<<< imperium/src/main/java/com/imperium/imperium/controller/PageController.java
        model.addAttribute("username", UserController.getUser().getUsername());
=======
        model.addAttribute("username", UserController.getCurrentUser().getUsername());
>>>>>>> imperium/src/main/java/com/imperium/imperium/controller/PageController.java
        model.addAttribute("allUsers", userService.findAll());

        model.addAttribute("myProjects", projectService.findProjectByUserId(UserController.getCurrentUser().getId()));
        model.addAttribute("sharedProjects",
                projectService.getArrayProjectByArrayidProject(
                        accessService.findIdProjectSharedWithUserId(UserController.getCurrentUser().getId())));

        if (!error.equals("no-error"))
            model.addAttribute("error", "You Already have a project with the same name");

        return "home";
    }

<<<<<<< imperium/src/main/java/com/imperium/imperium/controller/PageController.java
    @GetMapping(value = { "/home/create-project", "/home/open-project", "/home/create-list", "/home/create-task" })
    private String openProject(Model model,@RequestParam(value = "id", defaultValue = "error") Long id,
=======
    @GetMapping(value = { "/home/create-project", "/home/open-project" })
    private String openProject(Model model, @RequestParam(value = "id", defaultValue = "error") Long id,
>>>>>>> imperium/src/main/java/com/imperium/imperium/controller/PageController.java
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long userId = UserController.getCurrentUser().getId();
        final String name = projectService.findById((Long) id).getName();

        model.addAttribute("isOwner", (projectService.getProjectOwner(id).getId().equals(userId)));

        model.addAttribute("username", UserController.getCurrentUser().getUsername());

        // List des List of Projects
        model.addAttribute("listOfProjectList", projectListService.findProjectListByProjectId(id));

        // List des List of Projects
        model.addAttribute("listOfProjectList", projectListService.findProjectListByProjectId(id));
        model.addAttribute("listOfTasks", taskService.findAll());
        
       

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
