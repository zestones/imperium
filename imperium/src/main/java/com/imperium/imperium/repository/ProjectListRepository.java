package com.imperium.imperium.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.imperium.imperium.model.Board;

@Repository
public interface ProjectListRepository extends JpaRepository<Board, Long> {

    ArrayList<Board> findProjectListByProjectId(Long id);

    ArrayList<Board> findAll();

    ArrayList<Board> findByTitle(String name);

    Optional<Board> findById(Long Id);

    ArrayList<Board> findByTitleAndProjectId(String Title, Long id);
}
