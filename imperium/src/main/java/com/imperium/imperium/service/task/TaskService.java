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
    TaskRepository taskRepo;

    @Override
    public void save(Task t){
        taskRepo.save(t);
    }
    @Override
    public void delete(Task t){
        taskRepo.delete(t);
    }
    /*@Override
    public ArrayList<Task> findTaskByProjectListId(Long id){
        return taskRepo.findTaskByProjectListId()
    }*/
    @Override
    public ArrayList<Task> findAll(){
        return taskRepo.findAll();
    }
     @Override
    public ArrayList<Task>findByTitle(String name){
        return taskRepo.findByTitle(name);
    }
   /*  @Override
    public ArrayList<Task> findByTitleAndProjectListId(String Title,  Long id){

    }*/
    @Override
    public Optional<Task> findById(Long id){
        return taskRepo.findById(id);
    }
}
