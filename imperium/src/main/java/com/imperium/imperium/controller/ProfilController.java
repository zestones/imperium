package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.imperium.imperium.service.user.UserService;

@Controller
public class ProfilController {

    @Autowired
    UserService userService;

    /*
     * @PostMapping(value = "/process-profil")
     * private String changeParamProfile(Model model, User user) {
     * 
     * return "redirect:/home/profile";
     * }
     */
}
