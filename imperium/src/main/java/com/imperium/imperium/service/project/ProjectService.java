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

    /********************************
     ** TEST PROJECT
     ********************************/

    /**
     * @param p      : Project object
     * @param userId : User id property
     * @return Boolean
     */
    public Boolean canCreateProject(Project p, Long userId) {
        ArrayList<Project> myProjects = pRepo.findProjectByUserId(userId);
        return !myProjects.stream().anyMatch(val -> p.getName().equals(val.getName()));
    }

    /**
     * @param u    : User object
     * @param name : Project name property
     * @return Boolean
     */
    public Boolean canDeleteProject(User u, String name) {
        Project p = findProjectByUserIdAndName(u.getId(), name);
        return (p != null);
    }

    /********************************
     ** FIND IN DB
     ********************************/

    /**
     * @param id : Project id property
     * @return Project
     */
    @Override
    public Project findById(Long id) {
        return pRepo.findProjectById(id);
    }

    /**
     * @return ArrayList<Project>
     */
    @Override
    public ArrayList<Project> findAll() {
        return (ArrayList<Project>) pRepo.findAll();
    }

    /**
     * @param id : Project id property
     * @return User
     */
    @Override
    public User findProjectOwner(Long id) {
        return pRepo.findProjectById(id).getUser();
    }

    /**
     * @param id : User id property
     * @return ArrayList<Project>
     */
    @Override
    public ArrayList<Project> findProjectByUserId(Long id) {
        return pRepo.findProjectByUserId(id);
    }

    /**
     * @param id   : User id property
     * @param name : Project name property
     * @return Project
     */
    @Override
    public Project findProjectByUserIdAndName(Long id, String name) {
        return pRepo.findProjectByUserIdAndName(id, name);
    }

    /********************************
     ** UPDATE DB
     ********************************/

    /**
     * @param p : Project object
     */
    @Override
    public void save(Project p) {
        pRepo.save(p);
    }

    /**
     * @param p : Project object
     */
    @Override
    public void delete(Project p) {
        pRepo.delete(p);
    }

    /********************************
     ** PROCESS DATA LOCALLY
     ********************************/

    /**
     * @param arrId : Array of Project id
     *              Get ArrayList of Project from an array list of id Project
     * @return ArrayList<Project>
     */
    public ArrayList<Project> getArrayProjectByArrayidProject(ArrayList<Long> arrId) {
        ArrayList<Project> arrProj = new ArrayList<>();

        for (Long id : arrId)
            arrProj.add(findById(id));

        return arrProj;
    }

}
