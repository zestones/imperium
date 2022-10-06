package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Access;
import com.imperium.imperium.service.access.AccessService;
import com.imperium.imperium.service.project.ProjectService;

@Controller
public class AccessController {
    @Autowired
    AccessService service;

    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/share-project/{id}")
    private String shareProject(@PathVariable Long id, Access a, String username) {

        System.out.println(a.toString());

        // TODO : check if user exist + add user to project + give access
        return "redirect:/open-project?name=" + projectService.findProjectById(id).getName();
    }

}
