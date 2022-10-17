package com.imperium.imperium.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.photo.FileUploadUtil;
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

    // @PostMapping(value = "/home/profile/process-profil")
    // public String updateUser(Model model, User u, String pwd1, String pwd2) {

    // if (!service.canUpdate(u, getCurrentUser()))
    // return "redirect:/home/profile?error=username";

    // u.setId(getCurrentUser().getId());

    // String pwd;
    // if (service.canUpdatePassword(u, pwd1, pwd2))
    // pwd = service.encodePassword(pwd2);
    // else if (u.getPassword().equals(""))
    // pwd = getCurrentUser().getPassword();
    // else
    // return "redirect:/home/profile?error=password";

    // u.setPassword(pwd);
    // service.update(u);
    // setCurrentUser(service.findById(u.getId()));

    // return "redirect:/home/profile";
    // }

    @PostMapping(value = "/home/profile/process-profil")
    public RedirectView updateUser(Model model, User u, String pwd1, String pwd2,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (!service.canUpdate(u, getCurrentUser()))
            return new RedirectView("/home/profil/home/profile?error=username", true);
        System.out.println("************1- NOT PASS***************");

        u.setId(getCurrentUser().getId());

        System.out.println("************PASS***************");
        String fileName = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("************2- NOT PASS***************");

        u.setPhotos(fileName);
        System.out.println("************PASS PHOTO SET***************");

        // User savedUser = urepo.save(u);
        String uploadDir = "src/main/resources/templates/user-photos/icon.png";
        System.out.println("************PASS STRING SET***************");
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        System.out.println("************3- NOT PASS***************");

        String pwd;
        if (service.canUpdatePassword(u, pwd1, pwd2))
            pwd = service.encodePassword(pwd2);
        else if (u.getPassword().equals(""))
            pwd = getCurrentUser().getPassword();
        else
            return new RedirectView("/home/profil/home/profile?error=username", true);

        u.setPassword(pwd);
        System.out.println("************PASS***************");

        service.update(u);
        // service.updateAll(u, multipartFile);
        System.out.println("************ PASS***************");

        setCurrentUser(service.findById(u.getId()));
        System.out.println("************PASS***************");

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
