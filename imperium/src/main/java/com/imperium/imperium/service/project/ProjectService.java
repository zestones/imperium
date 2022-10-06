package com.imperium.imperium.service.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.repository.ProjectRepository;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    ProjectRepository pRepo;

    @Override
    public void save(Project p) {
        pRepo.save(p);
    }

    public Boolean canCreateProject(Project p, Long userId) {
        ArrayList<Project> myProjects = pRepo.findProjectByUserId(userId);
        return myProjects.stream().anyMatch(val -> p.getName().equals(val.getName()));
    }

    @Override
    public ArrayList<Project> findProjectByUserId(Long id) {
        return pRepo.findProjectByUserId(id);
    }

}
