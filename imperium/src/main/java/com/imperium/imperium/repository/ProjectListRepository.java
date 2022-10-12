package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imperium.imperium.model.ProjectList;

@Repository
public interface ProjectListRepository extends JpaRepository<ProjectList, Long> {
    ArrayList<ProjectList> findProjectListByProjectId(Long id);
    ArrayList<ProjectList> findAll();
    ProjectList findByTitle(String name);
    ProjectList findProjectListById(Long Id);
    ArrayList<ProjectList> findByTitleAndProjectId(String Title,  Long id);
}

