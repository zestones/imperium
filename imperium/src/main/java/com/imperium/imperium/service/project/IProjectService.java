package com.imperium.imperium.service.project;

import java.util.ArrayList;

import com.imperium.imperium.model.Project;

public interface IProjectService {

    void save(Project p);

    ArrayList<Project> findProjectByUserId(Long id);

    ArrayList<Project> findAll();

    void delete(Project p);

    Project findProjectByUserIdAndName(Long id, String name);
}
