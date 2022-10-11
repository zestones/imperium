package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.user.UserService;

@Controller
public class ProfilController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/process-profile")
    private String changeParamProfile(Model model, User u) {
        Long userId = UserController.getUser().getId();
        service.findById(userId);
        service.save(u);
        return "redirect:/home/profile";
    }
}
