package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.service.project.ProjectService;

/* Controller class for Project page */
@Controller
public class ProjectController {

    @Autowired
    ProjectService service;
/* method for create new project, check if doesnt exist, set the user role and redirect into create project html page */
    @PostMapping(value = "/home/create-project")
    private String creatProject(Model model, Project p) {

        if (service.canCreateProject(p, UserController.getCurrentUser().getId())) {

            p.setUser(UserController.getCurrentUser());
            service.save(p);

            return "redirect:/home/create-project?id=" + p.getId();
        }

        return "redirect:/home?error=name";
    }
/* method for redirect into actual project link to the user Id */
    @GetMapping(value = "/home/open-project/{id}")
    private String openProject(@PathVariable Long id, Model model) {
        return "redirect:/home/open-project?id=" + id;
    }
/* method for delete a project check if user have right by its Id */
    @GetMapping(value = "/home/delete-project/{name}")
    private String deleteProject(@PathVariable String name) {

        if (service.canDeleteProject(UserController.getCurrentUser(), name)) {
            service.delete(service.findProjectByUserIdAndName(UserController.getCurrentUser().getId(), name));
        }

        return "redirect:/home";
    }
}
