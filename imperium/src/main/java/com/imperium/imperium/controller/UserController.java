package com.imperium.imperium.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.Part;

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

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws ServletException {

        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }

    @PostMapping(value = "/home/profile/process-profil")
    public RedirectView updateUser(String pwd1, String pwd2, @Validated User u,
            Model model, HttpServletRequest httpServletRequest,
            @RequestParam(value = "imagefile", required = false) Part imagefile) {

        if (!service.canUpdate(u, getCurrentUser()))
            return new RedirectView("/home/profil/home/profile?error=username", true);

        u.setId(getCurrentUser().getId());

        /* IMAGE UPLOAD */
        if (imagefile != null) {

            byte[] filecontent = null;
            try {
                InputStream inputStream = imagefile.getInputStream();
                if (inputStream == null)

                    filecontent = IOUtils.toByteArray(inputStream);
                u.setImagePhoto(filecontent);

            } catch (IOException ex) {
            }
            u.setImagePhoto(filecontent);
        }

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
        setCurrentUser(service.saveImage(u));
        setCurrentUser(service.findById(u.getId()));
        return new RedirectView("/home/profile", true);
    }

    @RequestMapping(value = "home/profile/{id}", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    byte[] getImage(HttpServletResponse req, @PathVariable Long id) {
        User u = service.findById(id);
        return u.getImagePhoto();
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
