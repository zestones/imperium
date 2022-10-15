package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Board;
import com.imperium.imperium.service.board.BoardService;
import com.imperium.imperium.service.project.ProjectService;

@Controller
public class BoardController {

    @Autowired
    BoardService service;

    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/home/create-board/{id}")
    private String createList(Model model, Board b, @PathVariable Long id) {

        b.setProject(projectService.findById(id));
        service.save(b);

        return "redirect:/home/create-board?id=" + id;
    }

}
