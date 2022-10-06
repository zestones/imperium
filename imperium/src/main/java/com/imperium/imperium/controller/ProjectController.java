package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.service.project.ProjectService;

@Controller
public class ProjectController {
    @Autowired
    ProjectService service;

    @PostMapping(value = "/create")
    public String creatProject(Project p) {

        if (service.canCreateProject(p, UserController.getUserId()))
            service.save(p);

        return "home";
    }

}
