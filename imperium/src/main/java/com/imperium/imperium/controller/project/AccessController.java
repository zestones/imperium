package com.imperium.imperium.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Access;
import com.imperium.imperium.model.Project;
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

    /**
     * @param id       : Project id (PathVariable)
     * @param a        : Access object
     * @param username : User username property
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/project/share-project/{id}")
    private String shareProject(@PathVariable Long id, Access a, String username) {
        Project p = projectService.findById(id);

        if (service.canShareProject(userService.findByUsername(username), projectService.findProjectOwner(id), id)) {

            a.setUser(userService.findByUsername(username));
            a.setProjects(projectService.findById(id));
            a.setAccess(a.getCanRead());

            service.save(a);
            return "redirect:/home/project/" + p.getUser().getUsername() + "/" + p.getName();
        }

        return "redirect:/home/project/" + p.getUser().getUsername() + "/" + p.getName() + "?error=username";
    }
}
