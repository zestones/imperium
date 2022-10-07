package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Access;
import com.imperium.imperium.service.access.AccessService;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class AccessController {
    @Autowired
    AccessService service;

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/share-project/{id}")
    private String shareProject(@PathVariable Long id, Access a, String username) {

        if (service.canShareProject(userService.findByUsername(username), projectService.getProjectOwner(id), id)) {

            a.setUser(userService.findByUsername(username));
            a.setProjects(projectService.findById(id));
            a.setAccess(a.getCanRead());

            service.save(a);
            return "redirect:/open-project?id=" + id;
        }

        // TODO : check if user exist + add user to project + give access
        return "redirect:/open-project?id=" + id + "&error=username";

    }

}
