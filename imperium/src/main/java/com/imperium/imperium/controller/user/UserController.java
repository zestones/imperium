package com.imperium.imperium.controller.user;

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
     * @param u : User object
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/profile/settings/update-account")
    private String updateAccount(User u) {

        u.setId(getCurrentUser().getId());
        u.setAvatar(getCurrentUser().getAvatar());
        u.setPassword(getCurrentUser().getPassword());
        u.setUsername(getCurrentUser().getUsername());

        setCurrentUser(u);

        service.update(u);

        return "redirect:/home/profile/account-settings";
    }

    /**
     * @param model  : holder for model attributes
     * @param oldPwd : the old password
     * @param pwd1   : the new password
     * @param pwd2   : the confirmation of the password
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/profile/settings/update-password")
    public String updatePassword(Model model, String oldPwd, String pwd1, String pwd2) {

        // UPDATE THE PASSWORD
        String pwd;
        if (service.canUpdatePassword(getCurrentUser().getId(), oldPwd, pwd1, pwd2))
            pwd = service.encodePassword(pwd2);
        else if (oldPwd.equals(""))
            pwd = getCurrentUser().getPassword();
        else
            return "redirect:/home/profile/account-settings?error=password";

        getCurrentUser().setPassword(pwd);
        service.update(getCurrentUser());

        return "redirect:/home/profile/account-settings";
    }

    /**
     * @param username : User username property
     * @return String : reditrect to PageController
     */
    @PostMapping(value = "/home/profile/settings/update-username")
    private String updateUsername(String username) {
        if (!service.canUpdate(username, getCurrentUser()))
            return "redirect:/home/profile/account-settings?error=username";

        getCurrentUser().setUsername(username);
        service.update(getCurrentUser());

        return "redirect:/home/profile/account-settings";
    }

    /**
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/profile/settings/delete-account")
    private String deleteAccount() {
        service.delete(getCurrentUser());
        return "redirect:/";
    }

    /**
     * @param u : User object
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/profile/settings/update-social-media")
    private String updateSocialMedia(User u) {

        getCurrentUser().setGithub(u.getGithub());
        getCurrentUser().setLinkedin(u.getLinkedin());
        getCurrentUser().setTwitter(u.getTwitter());
        getCurrentUser().setStackOverflow(u.getStackOverflow());

        service.update(getCurrentUser());

        return "redirect:/home/profile/account-settings";
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