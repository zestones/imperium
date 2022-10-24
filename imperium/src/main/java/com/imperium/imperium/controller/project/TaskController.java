package com.imperium.imperium.controller.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Project;
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
    @PostMapping(value = "/home/project/create-task/{idBoard}")
    public String createTask(Model model, Task t, @PathVariable Long idBoard) {

        t.setBoard(boardService.findBoardById(idBoard));
        service.save(t);

        Project p = t.getBoard().getProject();
        return "redirect:/home/project/" + p.getUser().getUsername() + "/" + p.getName();
    }

    /**
     * @param idTask   : Task id property (PathVariable)
     * @param idProjet : Project id property (PathVariable)
     * @return String : redirect to PageController
     */
    @GetMapping(value = "/home/project/delete-task/{idTask}")
    public String deleteTask(@PathVariable Long idTask) {

        Project p = service.findById(idTask).getBoard().getProject();
        service.deleteById(idTask);

        return "redirect:/home/project/" + p.getUser().getUsername() + "/" + p.getName();
    }

    /**
     * 
     * @param idTask    : Task id property (PathVariable)
     * @param idUser    : User id property (PathVariable)
     * @param idProject : Project id property (PathVariable)
     * @return String : redirect to PageController
     */
    @GetMapping(value = "/home/project/assign-task/{idTask}/{idUser}")
    public String assignUserTask(@PathVariable Long idTask, @PathVariable Long idUser) {

        Task t = service.findById(idTask);
        List<User> contributors = t.getUsers();

        contributors.add(userService.findById(idUser));
        t.setUsers(contributors);
        service.save(t);

        Project p = service.findById(idTask).getBoard().getProject();
        return "redirect:/home/project/" + p.getUser().getUsername() + "/" + p.getName();
    }
}
