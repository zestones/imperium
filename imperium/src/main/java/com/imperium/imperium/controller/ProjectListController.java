package com.imperium.imperium.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.ProjectList;
import com.imperium.imperium.service.ProjectList.ProjectListService;
import com.imperium.imperium.service.project.ProjectService;

@Controller
public class ProjectListController {
    @Autowired
    ProjectListService service;
    @Autowired
    ProjectService projectService;
    

    @PostMapping(value = "/home/create-list/{id}")
    private String createList(Model model, ProjectList l,@PathVariable Long id) {
        l.setProject(projectService.findById(id));
        service.save(l);
        return "redirect:/home/create-list?id=" + id;
    }



}
