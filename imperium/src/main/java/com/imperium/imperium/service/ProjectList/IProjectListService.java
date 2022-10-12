package com.imperium.imperium.service.ProjectList;

import java.util.ArrayList;

import com.imperium.imperium.model.ProjectList;

public interface IProjectListService {

    void save(ProjectList p);

    void delete(ProjectList p);

    ArrayList<ProjectList> findProjectListByProjectId(Long id);

    ArrayList<ProjectList> findAll();

    ProjectList findByTitle(String Name);
    
    ArrayList<ProjectList> findByTitleAndProjectId(String Title,  Long id);

    ProjectList findById(Long Id);
   


    
}
