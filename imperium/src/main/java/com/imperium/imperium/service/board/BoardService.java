package com.imperium.imperium.service.board;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Board;
import com.imperium.imperium.repository.ProjectListRepository;

@Service
public class BoardService implements IBoardService {
    @Autowired
    ProjectListRepository projectListRepo;

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
    public ArrayList<Board> findByTitle(String Name) {
        return projectListRepo.findByTitle(Name);
    }

    @Override
    public ArrayList<Board> findByTitleAndProjectId(String Title, Long id) {
        return projectListRepo.findByTitleAndProjectId(Title, id);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return projectListRepo.findById(id);
    }

}
