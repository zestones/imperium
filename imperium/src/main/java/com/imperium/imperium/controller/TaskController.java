package com.imperium.imperium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.Task;
import com.imperium.imperium.service.board.BoardService;
import com.imperium.imperium.service.task.TaskService;

/* Controller class for the task manager methods */
@Controller
public class TaskController {

    @Autowired
    TaskService service;

    @Autowired
    BoardService boardService;
/* method for new task into project call boardService class for set into board, save and redirect into open project html page */
    @PostMapping(value = "/home/create-task")
    public String createTask(Model model, Task t, @RequestParam("idBoard") Long idBoard) {

        t.setBoard(boardService.findBoardById(idBoard));
        service.save(t);
        Long id = t.getBoard().getProject().getId();

        return "redirect:/home/open-project?id=" + id;
    }
/* method for task deletion call service class and deleteById method, redirect into open project html page */
    @GetMapping(value = "/home/delete-task/{idTask}/{idProjet}")
    public String deleteTask(@PathVariable Long idTask, @PathVariable Long idProjet) {

        service.deleteById(idTask);
        return "redirect:/home/open-project?id=" + idProjet;
    }

}
