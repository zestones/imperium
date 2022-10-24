package com.imperium.imperium.controller.page;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.controller.user.UserController;
import com.imperium.imperium.service.access.AccessService;
import com.imperium.imperium.service.followers.FollowersService;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    AccessService accessService;

    @Autowired
    FollowersService followersService;

    /**
     * @param model   : holder for model attributes
     * @param "error" : error (RequestParam)
     * @param error   : Boolean for error parameter
     * @return String : return the home file
     */
    @GetMapping(value = "/home")
    private String homePage(Model model, @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long id = UserController.getCurrentUser().getId();

        LocalDate date = LocalDate.now();
        model.addAttribute("month", date.getMonth());
        model.addAttribute("day", date.getDayOfMonth());

        // USER DATA
        model.addAttribute("user", UserController.getCurrentUser());

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

        // FOLLOWERS / FOLLOWINGS
        model.addAttribute("following",
                userService.getArrayUserByArrayidUser(
                        followersService.findIdUserFollowing(UserController.getCurrentUser().getId())));

        model.addAttribute("follower",
                userService.getArrayUserByArrayidUser(
                        followersService.findIdUserFollower(UserController.getCurrentUser().getId())));

        // PROCESS ERROR MSG
        if (!error.equals("no-error"))
            model.addAttribute("error", "You Already have a project with the same name");

        return "home";
    }
}
