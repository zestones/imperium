package com.imperium.imperium.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imperium.imperium.model.ProjectList;

@Repository
public interface ProjectListRepository extends JpaRepository<ProjectList, Long> {

    ArrayList<ProjectList> findProjectListByProjectId(Long id);
    ArrayList<ProjectList> findAll();
    ArrayList<ProjectList> findByTitle(String name);
    Optional<ProjectList> findById(Long Id);
    ArrayList<ProjectList> findByTitleAndProjectId(String Title,  Long id);
}

