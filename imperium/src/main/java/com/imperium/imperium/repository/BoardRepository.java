package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imperium.imperium.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    ArrayList<Board> findProjectListByProjectId(Long id);

    ArrayList<Board> findAll();

    Board findByTitle(String name);

    Board findProjectListById(Long Id);

    ArrayList<Board> findByTitleAndProjectId(String Title, Long id);
}
