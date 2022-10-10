package com.imperium.imperium.service.list;

import java.util.ArrayList;


import com.imperium.imperium.model.ProjectList;

public interface IProjectListService {
    void save(ProjectList p);

    void delete(ProjectList p);

    ArrayList<ProjectList> findProjectListByProjectId(Long id);

    ArrayList<ProjectList> findAll();

    ProjectList findProjectListByName(Long id_project, String name);

    ProjectList findById(Long id);
}
