package com.imperium.imperium.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.imperium.imperium.model.ProjectList;
import com.imperium.imperium.service.ProjectList.ProjectListService;

@Controller
public class ProjectListController {
    @Autowired
    ProjectListService projectListService;
    
    /*@GetMapping("/home/projectList")
    public String getAllProjectList(Model model){
        model.addAttribute("projectList",projectListService.findAll());
        return "projectList";
    }*/

    @GetMapping("/home/projectList/{id}")
    public String getProjectListByProjectId(@PathVariable( value="id") Long id, Model model){
        ArrayList<ProjectList> projectList = projectListService.findProjectListByProjectId(id);
        model.addAttribute("projectList", projectList);
        return "projectList";
    }

    /*@GetMapping("/home/projectList/{title}")
    public  String findByTitle( @PathVariable(value="title") String title, Model model){
        model.addAttribute("projectList",projectListService.findByTitle(title));
        return "projectList";
    }*/

    /*@GetMapping("/home/projectList/{id}/{title}")
    public  String findByTitleAndProjectId( @PathVariable(value="title") String title,@PathVariable(value="id") Long id, Model model){
       model.addAttribute("projectList", projectListService.findByTitleAndProjectId(title, id));
       return "projectList";
    }

    /*@GetMapping("/home/projectList/{id}")
    public String findById(@PathVariable( value="id") Long id, Model model){
        model.addAttribute("projectList",projectListService.findById(id));
        return "projectList";
    }*/

}