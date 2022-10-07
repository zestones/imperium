package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imperium.imperium.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    ArrayList<Project> findAll();

    Project findProjectById(Long id);

    ArrayList<Project> findProjectByUserId(Long id);

    Project findProjectByUserIdAndName(Long id, String name);
}
