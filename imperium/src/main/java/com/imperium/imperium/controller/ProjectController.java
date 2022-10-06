package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class ProjectController {
    @Autowired
    ProjectService service;
    @Autowired
    UserService userService;

    @PostMapping(value = "/create")
    public String creatProject(Model model, Project p) {

        if (service.canCreateProject(p, UserController.getUserId())) {

            p.setUser(userService.findById(UserController.getUserId()));
            service.save(p);

            return "redirect:/create-project?name=" + p.getName();
        }

        return "redirect:/home?username=" + userService.findById(UserController.getUserId()).getUsername()
                + "&error=name";
    }

}
