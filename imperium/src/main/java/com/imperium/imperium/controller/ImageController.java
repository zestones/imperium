package com.imperium.imperium.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.parser.MediaType;
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

    // @GetMapping(value = "/home/profile/image-process")
    // public String show(@PathVariable Long id, Model model) {
    // User uImage = uService.findById(id);
    // System.out.println("*************************PASS1**********************");

    // if (uImage.getImagePhoto() == null) {
    // System.out.println("*************************PASS2**********************");

    // logger.debugv("Downloading photo for id: {} with size{}",
    // uImage.getId(), uImage.getImagePhoto().length);
    // }
    // model.addAttribute("uImage", uImage);
    // return "redirect:/home/profile";
    // }

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
        System.out.println("*************************PASS1**********************");
        // process upload file
        if (imagefile != null) {
            System.out.println("*************************PASS2**********************");

            byte[] filecontent = null;
            try {
                System.out.println("*************************PASS3**********************");
                InputStream inputStream = imagefile.getInputStream();
                System.out.println("*************************PASS4**********************");
                if (inputStream == null)
                    System.out.println("*************************PASS5**********************");

                filecontent = IOUtils.toByteArray(inputStream);
                uImage.setImagePhoto(filecontent);
                System.out.println("*************************PASS6**********************");

            } catch (IOException ex) {
                System.out.println("*************************PASS7**********************");
            }
            System.out.println("*************************PASS8**********************");
            uImage.setImagePhoto(filecontent);

        }
        System.out.println("*************************PASSUPDATE**********************");

        uService.update(uImage);
        System.out.println("*************************PASSUPDATE**********************");

        return "redirect:/home/profile";
    }

    @RequestMapping(value = "home/profile/{id}", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    byte[] getImage(HttpServletResponse req, @PathVariable Long id) {
        User u = uService.findById(id);
        return u.getImagePhoto();
    }
}
