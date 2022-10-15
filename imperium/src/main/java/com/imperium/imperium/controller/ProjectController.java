package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.service.ProjectList.BoardService;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.task.TaskService;

@Controller
public class ProjectController {
    @Autowired
    ProjectService service;

    @Autowired
    TaskService taskservice;

    @Autowired
    BoardService projectListService;

    @PostMapping(value = "/home/create-project")
    private String creatProject(Model model, Project p) {

        if (service.canCreateProject(p, UserController.getUser().getId())) {

            p.setUser(UserController.getUser());
            service.save(p);

            return "redirect:/home/create-project?id=" + p.getId();
        }

        return "redirect:/home?error=name";
    }

    @GetMapping(value = "/home/open-project/{id}")
    private String openProject(@PathVariable Long id, Model model) {
        model.addAttribute("listOfProjectList", projectListService.findAll());
        return "redirect:/home/open-project?id=" + id;
    }

    @GetMapping(value = "/home/delete-project/{name}")
    private String deleteProject(@PathVariable String name) {

        if (service.canDeleteProject(UserController.getUser(), name)) {
            service.delete(service.findProjectByUserIdAndName(UserController.getUser().getId(), name));
        }

        return "redirect:/home";
    }
}
