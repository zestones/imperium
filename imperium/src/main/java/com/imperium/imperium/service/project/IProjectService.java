package com.imperium.imperium.service.project;

import java.util.ArrayList;

import com.imperium.imperium.model.Project;

public interface IProjectService {

    void save(Project p);

    void delete(Project p);

    ArrayList<Project> findProjectByUserId(Long id);

    ArrayList<Project> findAll();

    Project findProjectByUserIdAndName(Long id, String name);

    Project findById(Long id);
}
