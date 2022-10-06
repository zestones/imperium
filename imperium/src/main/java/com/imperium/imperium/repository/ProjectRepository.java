package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.imperium.imperium.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    ArrayList<Project> findProjectByUserId(Long id);

    // Project findProjectById(Long id);
}
