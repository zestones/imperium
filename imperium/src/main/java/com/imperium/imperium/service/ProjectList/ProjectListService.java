package com.imperium.imperium.service.ProjectList;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.ProjectList;
import com.imperium.imperium.repository.ProjectListRepository;

@Service
public class ProjectListService implements IProjectListService{
    @Autowired 
    ProjectListRepository projectListRepo;

    @Override
    public void save(ProjectList p) {
        projectListRepo.save(p);
        
    }

    @Override
    public void delete(ProjectList p) {
        projectListRepo.delete(p);
        
    }

    @Override
    public ArrayList<ProjectList> findProjectListByProjectId(Long id) {
        return projectListRepo.findProjectListByProjectId(id);
    }

    @Override
    public ArrayList<ProjectList> findAll() {
       return projectListRepo.findAll();
    }

    @Override
    public ProjectList findByTitle(String Name) {
        return projectListRepo.findByTitle(Name);
    }

    @Override
    public ArrayList<ProjectList> findByTitleAndProjectId(String Title, Long id) {
        return projectListRepo.findByTitleAndProjectId(Title, id);
    }

    
    @Override
    public  ProjectList findById(Long id) {
       return projectListRepo.findProjectListById(id);
    }

}
