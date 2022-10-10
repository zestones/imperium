package com.imperium.imperium.service.list;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.model.ProjectList;
import com.imperium.imperium.repository.ProjectListRepository;

public class ProjectListService implements IProjectListService {
    @Autowired
    ProjectListRepository lRepo;

    @Override
    public void save(ProjectList l) {
        lRepo.save(l);
    }

    @Override
    public void delete(ProjectList l){
        lRepo.delete(l);
    }
    
    public ArrayList<ProjectList> findProjectListByProjectId(Long id){
        return lRepo.findProjectListByProjectId(id);
    }

    ArrayList<ProjectList> findAll(){}

    ProjectList findProjectListByName(Long id_project, String name){}

    ProjectList findById(Long id){}
}
