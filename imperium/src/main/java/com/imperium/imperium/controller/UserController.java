package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.user.UserService;

/* Controller class for all User PostMapping methods */
@Controller
public class UserController {

    @Autowired
    private UserService service;

    private static User user;
/* method for signIn check if already registered if not save user and redirect into home page html */
    @PostMapping(value = "/signIn")
    public String signIn(Model model, User u) {
        String pwd = u.getPassword();

        if (service.isUserRegistered(u)) {
            model.addAttribute("error", "Username already used.");
            return "authentification/signIn";
        }

        service.save(u);
        setCurrentUser(u);

        service.autologin(u.getUsername(), pwd);

        return "redirect:/home";
    }
/* method logIn check if user already registered if not return error else logIn into home page html*/
    @PostMapping(value = "/process-logIn")
    public String logIn(Model model, User u) {
        if (service.canConnect(u)) {
            setCurrentUser(service.findByUsername(u.getUsername()));
            return "redirect:/home";
        }

        return "authentification/logIn";
    }

/* method updateUser check if user exist else get currentUser Id, call service user class for getPassword key, set the new update into service and call current user for set it*/
    @PostMapping(value = "/home/profile/process-profil")
    public String updateUser(Model model, User u, String pwd1, String pwd2) {

        if (!service.canUpdate(u, getCurrentUser()))
            return "redirect:/home/profile?error=username";

        u.setId(getCurrentUser().getId());

        String pwd;
        if (service.canUpdatePassword(u, pwd1, pwd2))
            pwd = service.encodePassword(pwd2);
        else if (u.getPassword().equals(""))
            pwd = getCurrentUser().getPassword();
        else
            return "redirect:/home/profile?error=password";

        u.setPassword(pwd);
        service.update(u);
        setCurrentUser(service.findById(u.getId()));

        return "redirect:/home/profile";
    }

    public static void setCurrentUser(User u) {
        user = u;
    }

    public static User getCurrentUser() {
        return user;
    }

}
