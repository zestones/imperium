package com.imperium.imperium.service.task;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Task;
import com.imperium.imperium.repository.TaskRepository;

@Service
public class TaskService implements ITaskService {

    @Autowired
    TaskRepository tRepo;

    @Override
    public void save(Task t) {
        tRepo.save(t);
    }

    public void deleteById(Long id) {
        tRepo.deleteById(id);
    }

    @Override
    public ArrayList<Task> findTaskByProjectId(Long id) {
        return tRepo.findTaskByProjectId(id);
    }
}
