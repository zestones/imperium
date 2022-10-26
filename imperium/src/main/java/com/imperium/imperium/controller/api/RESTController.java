package com.imperium.imperium.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.model.User;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.user.UserService;

@RestController
@RequestMapping(value = "/api")
public class RESTController {

    @Autowired
    private UserService service;

    @Autowired
    private ProjectService pservice;

    /* view api by id */
    @GetMapping(value = "/user/{id}")
    public User getUserInfos(@PathVariable long id) {
        return service.findById(id);
    }

    /* view api by list of user */

    @GetMapping(value = "/alluser")
    public List<User> getAllUserInfos() {
        return service.findAll();
    }

    /* view api by user id find project */

    @GetMapping(value = "/project/{id}")
    public ArrayList<Project> getProjectInfosByUsername(@PathVariable long id) {
        return pservice.findProjectByUserId(id);
    }

    /*
     * for use :
     * - login into /home
     * - tap in search bar : /api/user/1
     * - or : /api/alluser
     * - or : /api/project/1
     */
}
