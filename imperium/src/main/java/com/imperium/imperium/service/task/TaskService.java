package com.imperium.imperium.service.task;

import java.util.ArrayList;
import java.util.Optional;

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

    @Override
    public void delete(Task t) {
        tRepo.delete(t);
    }

    @Override
    public ArrayList<Task> findTaskByProjectId(Long id) {
        return tRepo.findTaskByProjectId(id);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tRepo.findById(id);
    }

    public void deleteById(Long id) {
        tRepo.deleteById(id);
    }

}
