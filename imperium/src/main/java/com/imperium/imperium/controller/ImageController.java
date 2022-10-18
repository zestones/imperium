package com.imperium.imperium.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

import com.imperium.imperium.model.User;
import com.imperium.imperium.service.user.UserService;

@Controller
public class ImageController {

    @Autowired
    UserService uService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws ServletException {

        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }

    @PostMapping(value = "/home/profile/image-process")
    public String saveImage(@Validated User uImage,
            Model model, HttpServletRequest httpServletRequest,
            @RequestParam(value = "imagefile", required = false) Part imagefile) {

        uImage.setId(UserController.getCurrentUser().getId());
        uImage.setFirstname(UserController.getCurrentUser().getFirstname());
        uImage.setUsername(UserController.getCurrentUser().getUsername());
        uImage.setLastname(UserController.getCurrentUser().getLastname());
        uImage.setPassword(UserController.getCurrentUser().getPassword());

        // process upload file
        if (imagefile != null) {

            byte[] filecontent = null;
            try {
                InputStream inputStream = imagefile.getInputStream();
                if (inputStream == null)

                    filecontent = IOUtils.toByteArray(inputStream);
                uImage.setImagePhoto(filecontent);

            } catch (IOException ex) {
            }
            uImage.setImagePhoto(filecontent);

        }

        uService.saveImage(uImage);
        UserController.setCurrentUser(uService.findById(uImage.getId()));

        return "redirect:/home/profile";
    }

    @RequestMapping(value = "home/profile/{id}", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    byte[] getImage(HttpServletResponse req, @PathVariable Long id) {
        User u = uService.findById(id);
        return u.getImagePhoto();
    }

}
