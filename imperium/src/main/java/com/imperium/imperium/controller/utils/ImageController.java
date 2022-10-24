package com.imperium.imperium.controller.utils;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.imperium.imperium.controller.user.UserController;
import com.imperium.imperium.model.User;
import com.imperium.imperium.service.user.UserService;

@Controller
public class ImageController {

    @Autowired
    UserService userService;

    /**
     * @param binder
     * @throws ServletException
     */
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws ServletException {

        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    /**
     * @param avatar : The uploaded avatar
     * @return String : redirect to the pageController
     */
    @PostMapping(value = "/home/profile/update-avatar")
    private String updateAvatar(byte[] avatar) {

        if (avatar.length == 0)
            return "redirect:/home/profile/account-settings";

        User u = UserController.getCurrentUser();
        u.setAvatar(avatar);
        userService.update(u);

        UserController.setCurrentUser(userService.findById(u.getId()));

        return "redirect:/home/profile/account-settings";
    }

    /**
     * @param id : User id property
     * @return byte[] : User avatar
     */
    @ResponseBody
    @GetMapping(value = "home/profile/user/{id}/avatar")
    private byte[] getImage(@PathVariable Long id) {
        return userService.findById(id).getAvatar();
    }
}
