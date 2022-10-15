package com.imperium.imperium.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imperium.imperium.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    ArrayList<Task> findAll();

    ArrayList<Task> findByTitle(String name);

    Optional<Task> findById(Long id);

    @Query("Select t from Task t where id_list = :idlist")
    ArrayList<Task> findByProjectListId(Long idlist);
}
