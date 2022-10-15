package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imperium.imperium.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findBoardById(Long id);

    ArrayList<Board> findBoardByProjectId(Long id);
}
