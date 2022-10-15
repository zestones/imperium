package com.imperium.imperium.service.ProjectList;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Board;
import com.imperium.imperium.repository.BoardRepository;

@Service
public class BoardService implements IBoardService {
    @Autowired
    BoardRepository projectListRepo;

    @Override
    public void save(Board p) {
        projectListRepo.save(p);
    }

    @Override
    public void delete(Board p) {
        projectListRepo.delete(p);
    }

    @Override
    public ArrayList<Board> findProjectListByProjectId(Long id) {
        return projectListRepo.findProjectListByProjectId(id);
    }

    @Override
    public ArrayList<Board> findAll() {
        return projectListRepo.findAll();
    }

    @Override
    public Board findByTitle(String Name) {
        return projectListRepo.findByTitle(Name);
    }

    @Override
    public ArrayList<Board> findByTitleAndProjectId(String Title, Long id) {
        return projectListRepo.findByTitleAndProjectId(Title, id);
    }

    @Override
    public Board findById(Long id) {
        return projectListRepo.findProjectListById(id);
    }

}
