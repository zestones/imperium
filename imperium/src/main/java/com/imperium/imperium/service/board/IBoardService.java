package com.imperium.imperium.service.board;

import java.util.ArrayList;
import java.util.Optional;

import com.imperium.imperium.model.Board;

public interface IBoardService {

    void save(Board p);

    void delete(Board p);

    ArrayList<Board> findProjectListByProjectId(Long id);

    ArrayList<Board> findAll();

    ArrayList<Board> findByTitle(String Name);

    ArrayList<Board> findByTitleAndProjectId(String Title, Long id);

    Optional<Board> findById(Long Id);

}
