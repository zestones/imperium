package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.user.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    private static User user;

    /**
     * @param model : holder for model attributes
     * @param u     : User object
     * @return String : redirect to PageController
     */
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

    /**
     * @param model : holder for model attributes
     * @param u     : User object
     * @param pwd1  : the new password
     * @param pwd2  : the confirmation of the password
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/profile/process-profil")
    public String updateUser(String pwd1, String pwd2, User u,
            Model model) {

        if (!service.canUpdate(u, getCurrentUser()))
            return "redirect:/home/profile?error=username";

        u.setId(getCurrentUser().getId());
        u.setAvatar(getCurrentUser().getAvatar());

        // UPDATE THE PASSWORD
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

    /**
     * @param u : User object
     */
    public static void setCurrentUser(User u) {
        user = u;
    }

    /**
     * @return User object
     */
    public static User getCurrentUser() {
        return user;
    }

}
