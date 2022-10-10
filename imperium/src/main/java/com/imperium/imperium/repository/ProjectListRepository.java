package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imperium.imperium.model.ProjectList;

@Repository
public interface ProjectListRepository extends JpaRepository<ProjectList, Long> {
    @Query(value = "SELECT * FROM PROJECTLIST l WHERE " +
                        "l.id_project = :projectId ",
                         nativeQuery = true)
    ArrayList<ProjectList> findProjectListByProjectId(@Param("projectId") Long id);
}
