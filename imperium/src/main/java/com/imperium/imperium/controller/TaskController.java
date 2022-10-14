package com.imperium.imperium.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.Task;
import com.imperium.imperium.service.ProjectList.ProjectListService;
import com.imperium.imperium.service.task.TaskService;

@Controller
public class TaskController {
    @Autowired
    TaskService service;
    @Autowired
    ProjectListService projectListService;

    @RequestMapping("/home/createNewTask")
    public String createTask(Model model, @ModelAttribute("task") Task t, @RequestParam("tasktitle") String title, @RequestParam("listid") Long idlist, @RequestParam("idproject") Long id)
    {
        t.setList(projectListService.findById(idlist));
        t.setTitle(title);
        service.save(t);
      
        return "redirect:/home/open-project?id="+id;
    }

  


   


}
