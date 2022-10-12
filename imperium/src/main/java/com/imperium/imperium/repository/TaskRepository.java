package com.imperium.imperium.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imperium.imperium.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
    //ArrayList<Task> findTaskByProjectListId(Long id);
    ArrayList<Task> findAll();
    ArrayList<Task> findByTitle(String name);
    Optional<Task> findById(Long id);
    //ArrayList<Task> findByTitleAndProjectListId(String Title,  Long id);
}
