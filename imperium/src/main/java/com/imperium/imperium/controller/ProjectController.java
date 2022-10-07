package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.service.project.ProjectService;

@Controller
public class ProjectController {
    @Autowired
    ProjectService service;

    @PostMapping(value = "/create-project")
    private String creatProject(Model model, Project p) {

        if (service.canCreateProject(p, UserController.getUser().getId())) {

            p.setUser(UserController.getUser());
            service.save(p);

            return "redirect:/create-project?name=" + p.getName();
        }

        return "redirect:/home?username=" + UserController.getUser().getUsername()
                + "&error=name";
    }

    @GetMapping(value = "/open-project/{id}")
    private String openProject(@PathVariable Long id) {

        return "redirect:/open-project?id=" + id;
    }

    @GetMapping(value = "/delete-project/{name}")
    private String deleteProject(@PathVariable String name) {

        // TODO : only owner can delete project && Check if has access to projects
        if (service.canDeleteProject(UserController.getUser(), name)) {
            service.delete(service.findProjectByUserIdAndName(UserController.getUser().getId(), name));
        }

        return "redirect:/home?username=" + UserController.getUser().getUsername();
    }
}
