package com.imperium.imperium.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.imperium.imperium.model.Board;
import com.imperium.imperium.service.board.BoardService;

@Controller
public class BoardController {
    @Autowired
    BoardService projectListService;

    @GetMapping("/home/project-list/{id}")
    public String getProjectListByProjectId(@PathVariable(value = "id") Long id, Model model) {

        ArrayList<Board> projectList = projectListService.findProjectListByProjectId(id);
        model.addAttribute("projectList", projectList);

        return "projectList";
    }

}
