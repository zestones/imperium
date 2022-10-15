package com.imperium.imperium.service.ProjectList;

import java.util.ArrayList;

import com.imperium.imperium.model.Board;

public interface IBoardService {

    void save(Board p);

    void delete(Board p);

    ArrayList<Board> findProjectListByProjectId(Long id);

    ArrayList<Board> findAll();

    Board findByTitle(String Name);

    ArrayList<Board> findByTitleAndProjectId(String Title, Long id);

    Board findById(Long Id);
}
