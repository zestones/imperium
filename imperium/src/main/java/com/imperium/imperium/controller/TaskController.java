package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.Task;
import com.imperium.imperium.service.board.BoardService;
import com.imperium.imperium.service.task.TaskService;

@Controller
public class TaskController {

    @Autowired
    TaskService service;

    @Autowired
    BoardService boardService;

    @PostMapping(value = "/home/create-task")
    public String createTask(Model model, Task t, @RequestParam("idBoard") Long idBoard) {

        t.setBoard(boardService.findBoardById(idBoard));
        service.save(t);
        Long id = t.getBoard().getProject().getId();

        return "redirect:/home/open-project?id=" + id;
    }

}
