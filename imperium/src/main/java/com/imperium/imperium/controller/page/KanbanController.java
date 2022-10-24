package com.imperium.imperium.controller.page;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.controller.user.UserController;
import com.imperium.imperium.model.User;
import com.imperium.imperium.service.access.AccessService;
import com.imperium.imperium.service.board.BoardService;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.task.TaskService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class KanbanController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    AccessService accessService;

    @Autowired
    BoardService boardService;

    @Autowired
    TaskService taskService;

    /**
     * @param model   : holder for model attributes
     * @param "id"    : Project id (RequestParam)
     * @param "error" : error (RequestParam)
     * @return String : return the project file
     */
    @GetMapping(value = { "/home/project/{username}/{projectName}" })
    private String openProject(Model model, @PathVariable String username, @PathVariable String projectName,
            @RequestParam(value = "error", defaultValue = "no-error") String error) {

        Long userId = UserController.getCurrentUser().getId();
        Long id = projectService.findProjectByUserIdAndName(
                userService.findByUsername(username).getId(), projectName).getId();

        // ! **** For Dev ****
        model.addAttribute("allUsers", userService.findAll());
        // ! *****************

        // USER DATA
        model.addAttribute("user", UserController.getCurrentUser());
        model.addAttribute("isOwner", (projectService.findProjectOwner(id).getId().equals(userId)));
        model.addAttribute("username", UserController.getCurrentUser().getUsername());

        // BOARD DATA
        model.addAttribute("boards", boardService.findBoardsByProjectId(id));
        model.addAttribute("tasks", taskService.findTaskByProjectId(id));

        // PROJECT DATA
        model.addAttribute("id", id);
        model.addAttribute("projectName", projectName);
        model.addAttribute("myProjects", projectService.findProjectByUserId(userId));

        ArrayList<User> l = userService.getArrayUserByArrayidUser(accessService.findIdContributorByIdProject(id));
        l.add(projectService.findProjectOwner(id));
        model.addAttribute("contributor", l);

        // PROCESS ERROR
        if (!error.equals("no-error"))
            model.addAttribute("error", "Error : Unable share the project !");

        return "project";
    }

}
