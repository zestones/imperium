package com.imperium.imperium.service.ProjectList;

import java.util.ArrayList;
import java.util.Optional;

import com.imperium.imperium.model.ProjectList;

public interface IProjectListService {

    void save(ProjectList p);

    void delete(ProjectList p);

    ArrayList<ProjectList> findProjectListByProjectId(Long id);

    ArrayList<ProjectList> findAll();

    ArrayList<ProjectList>findByTitle(String Name);
    
    ArrayList<ProjectList> findByTitleAndProjectId(String Title,  Long id);

    Optional<ProjectList> findById(Long Id);
   


    
}
