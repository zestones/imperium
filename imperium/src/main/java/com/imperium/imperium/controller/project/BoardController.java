package com.imperium.imperium.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Board;
import com.imperium.imperium.model.Project;
import com.imperium.imperium.service.board.BoardService;
import com.imperium.imperium.service.project.ProjectService;

@Controller
public class BoardController {

    @Autowired
    BoardService service;

    @Autowired
    ProjectService projectService;

    /**
     * @param model : holder for model attributes
     * @param b     : Board object
     * @param id    : Project id (PathVariable)
     * @return String : redirect to PageController
     */
    @PostMapping(value = "/home/project/create-board/{id}")
    private String createBoard(Model model, Board b, @PathVariable Long id) {
        Project p = projectService.findById(id);

        b.setProject(p);
        service.save(b);

        return "redirect:/home/project/" + p.getUser().getUsername() + "/" + p.getName();
    }

    /**
     * @param idBoard  : Board id property (PathVariable)
     * @param idProjet : Project id property (PathVariable)
     * @return String : redirect to PageController
     */
    @GetMapping(value = "/home/project/delete-board/{idBoard}/{idProjet}")
    public String deleteBoard(@PathVariable Long idBoard, @PathVariable Long idProjet) {

        service.deleteById(idBoard);

        Project p = projectService.findById(idProjet);
        return "redirect:/home/project/" + p.getUser().getUsername() + "/" + p.getName();
    }

}
