package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

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
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/process-logIn")
    public String logIn(Model model, User u) {
        if (service.canConnect(u)) {
            setCurrentUser(service.findByUsername(u.getUsername()));
            return "redirect:/home";
        }

        return "authentification/logIn";
    }

    // Need to be checked: Will not need password again to update other user infos

    // /**
    // * @param model : holder for model attributes
    // * @param u : User object
    // * @param pwd1 : the new password
    // * @param pwd2 : the confirmation of the password
    // * @return String : redirect to PageController
    // */

    @PostMapping(value = "/home/profile/process-profil")
    public RedirectView updateUser(String pwd1, String pwd2, User u,
            Model model) {

        if (!service.canUpdate(u, getCurrentUser()))
            return new RedirectView("/home/profil/home/profile?error=username", true);

        u.setId(getCurrentUser().getId());
        // u.setFirstname(getCurrentUser().getFirstname());
        // u.setUsername(getCurrentUser().getUsername());
        // u.setLastname(getCurrentUser().getLastname());
        u.setImagePhoto(getCurrentUser().getImagePhoto());

        /* PASSWORD UPLOAD */
        String pwd;
        if (service.canUpdatePassword(u, pwd1, pwd2))
            pwd = service.encodePassword(pwd2);
        else if (u.getPassword().equals(""))
            pwd = getCurrentUser().getPassword();
        else
            return new RedirectView("/home/profil/home/profile?error=username", true);

        u.setPassword(pwd);
        service.update(u);
        setCurrentUser(service.findById(u.getId()));
        return new RedirectView("/home/profile", true);
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
