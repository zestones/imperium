package com.imperium.imperium.controller;

import java.util.ArrayList;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.ProjectList;
import com.imperium.imperium.model.Task;
import com.imperium.imperium.service.ProjectList.ProjectListService;
import com.imperium.imperium.service.task.TaskService;

@Controller
public class TaskController {
    @Autowired
    TaskService service;
    @Autowired
    ProjectListService projectListService;

    /*
     * @GetMapping("/home/projectList")
     * public String getAllProjectList(Model model){
     * model.addAttribute("projectList",projectListService.findAll());
     * return "projectList";
     * }
     */

    @PostMapping(value = "/home/create-task/{title}/{id}")
    private String createTask(Model model, Task t, @PathVariable String title, @PathVariable Long id) {

        System.out.println("BONJOUR VOICI LA REQUEETE: " + projectListService.findByTitle(title));
        t.setList(projectListService.findByTitle(title));
        service.save(t);
        System.out.println("BONJOUR VOICI LA TASK : " + t);
        model.addAttribute("listTask", t);
        return "redirect:/home/create-task?id="+id;
    }

    @GetMapping("/home/open-task/{id}")
    public String getTask(@PathVariable( value="title") String title, Model model){
        model.addAttribute("listTask", service.findAll());
        return "task";
    }

}
