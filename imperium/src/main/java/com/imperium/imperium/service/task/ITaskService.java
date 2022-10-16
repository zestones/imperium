package com.imperium.imperium.service.task;

import java.util.ArrayList;
import java.util.Optional;

import com.imperium.imperium.model.Task;

public interface ITaskService {

  void save(Task t);

  void delete(Task t);

  ArrayList<Task> findTaskByProjectId(Long id);

  Optional<Task> findById(Long id);
}
