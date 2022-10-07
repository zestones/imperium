package com.imperium.imperium.service.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Project;
import com.imperium.imperium.model.User;
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
        return !myProjects.stream().anyMatch(val -> p.getName().equals(val.getName()));
    }

    public Boolean canDeleteProject(User u, String name) {
        Project p = findProjectByUserIdAndName(u.getId(), name);
        return (p != null);
    }

    public User getProjectOwner(Long id) {
        return pRepo.findProjectById(id).getUser();
    }

    @Override
    public ArrayList<Project> findProjectByUserId(Long id) {
        return pRepo.findProjectByUserId(id);
    }

    @Override
    public ArrayList<Project> findAll() {
        return (ArrayList<Project>) pRepo.findAll();
    }

    @Override
    public void delete(Project p) {
        pRepo.delete(p);
    }

    @Override
    public Project findProjectByUserIdAndName(Long id, String name) {
        return pRepo.findProjectByUserIdAndName(id, name);
    }

    @Override
    public Project findById(Long id) {
        return pRepo.findProjectById(id);
    }

    public ArrayList<Project> getArrayProjectByArrayidProject(ArrayList<Long> arrId) {
        ArrayList<Project> arrProj = new ArrayList<>();

        for (Long id : arrId)
            arrProj.add(findById(id));

        return arrProj;
    }
}
