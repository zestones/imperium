package com.imperium.imperium.service.project;

import java.util.ArrayList;

import com.imperium.imperium.model.Project;

public interface IProjectService {

    void save(Project p);

    ArrayList<Project> findProjectByUserId(Long id);
}
