package com.imperium.imperium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imperium.imperium.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
