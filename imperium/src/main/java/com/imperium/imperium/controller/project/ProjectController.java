package com.imperium.imperium.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.controller.user.UserController;
import com.imperium.imperium.model.Project;
import com.imperium.imperium.service.project.ProjectService;

@Controller
public class ProjectController {

    @Autowired
    ProjectService service;

    /**
     * @param model : holder for model attributes
     * @param p     : Project object
     * @return String : redirect PageController
     */
    @PostMapping(value = "/home/project/{username}/create-project")
    private String creatProject(Model model, Project p, @PathVariable String username) {

        if (service.canCreateProject(p, UserController.getCurrentUser().getId())) {

            p.setUser(UserController.getCurrentUser());
            service.save(p);

            return "redirect:/home/project/" + username + "/" + p.getName();
        }

        return "redirect:/home?error=name";
    }

    /**
     * @param name : Project name property (PathVariable)
     * @return String : redirect to PageController
     */
    @GetMapping(value = "/home/project/delete-project/{name}")
    private String deleteProject(@PathVariable String name) {

        if (service.canDeleteProject(UserController.getCurrentUser(), name)) {
            service.delete(service.findProjectByUserIdAndName(UserController.getCurrentUser().getId(), name));
        }

        return "redirect:/home";
    }
}
