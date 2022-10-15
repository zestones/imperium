package com.imperium.imperium.service.task;

import java.util.ArrayList;

import com.imperium.imperium.model.Task;

public interface ITaskService {

  void save(Task t);

  void delete(Task t);

  ArrayList<Task> findTaskByProjectId(Long id);
}
