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

    /********************************
     ** FIND IN DB
     ********************************/

    /**
     * @param id : Project id property
     * @return ArrayList<Task>
     */
    @Override
    public ArrayList<Task> findTaskByProjectId(Long id) {
        return tRepo.findTaskByProjectId(id);
    }

    /********************************
     ** UPDATE DB
     ********************************/

    /**
     * @param t : Task object
     */
    @Override
    public void save(Task t) {
        tRepo.save(t);
    }

    /**
     * @param id : Task id property
     */
    public void deleteById(Long id) {
        tRepo.deleteById(id);
    }
}
