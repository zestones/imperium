package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imperium.imperium.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT t.* FROM TASK t, BOARD b, PROJECTS p "
            + "WHERE t.id_board = b.id AND "
            + "b.id_project = p.id AND"
            + " p.id = :id", nativeQuery = true)
    ArrayList<Task> findTaskByProjectId(Long id);

    Task findTaskById(Long id);
}
