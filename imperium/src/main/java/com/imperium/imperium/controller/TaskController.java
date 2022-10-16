package com.imperium.imperium.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.model.Task;
import com.imperium.imperium.model.User;
import com.imperium.imperium.service.board.BoardService;
import com.imperium.imperium.service.task.TaskService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class TaskController {

    @Autowired
    TaskService service;

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    /**
     * @param model   : holder for model attributes
     * @param t       : Task object
     * @param idBoard : Board id property (RequestParam)
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/create-task")
    public String createTask(Model model, Task t, @RequestParam("idBoard") Long idBoard) {

        t.setBoard(boardService.findBoardById(idBoard));
        service.save(t);
        Long id = t.getBoard().getProject().getId();

        return "redirect:/home/open-project?id=" + id;
    }

    /**
     * @param idTask   : Task id property (PathVariable)
     * @param idProjet : Project id property (PathVariable)
     * @return String : redirect to PageController
     */
    @GetMapping(value = "/home/delete-task/{idTask}/{idProjet}")
    public String deleteTask(@PathVariable Long idTask, @PathVariable Long idProjet) {

        service.deleteById(idTask);
        return "redirect:/home/open-project?id=" + idProjet;
    }

    @GetMapping(value = "/home/assign-task/{idTask}/{idUser}/{idProject}")
    public String AssignUserTask(@PathVariable Long idTask, @PathVariable Long idUser, @PathVariable Long idProject) {
        List<User> listOfUsers = new ArrayList<User>();
        listOfUsers.add(userService.findById(idUser));
        service.findById(idTask).setUser(listOfUsers);
        return "redirect:/home/open-project?id=" + idProject;
    }
}
