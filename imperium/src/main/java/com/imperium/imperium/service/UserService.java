package com.imperium.imperium.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.User;
import com.imperium.imperium.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository uRepo;

    public Boolean canConnect(User u) {
        User cmp = uRepo.findByUsername(u.getUsername());
        return (isUserRegistered(u) && arePasswordMatching(u.getPassword(), cmp.getPassword()));
    }

    private Boolean arePasswordMatching(String pwd1, String pwd2) {
        return pwd1.equals(pwd2);
    }

    public Boolean isUserRegistered(User u) {
        return (uRepo.findByUsername(u.getUsername()) != null);
    }

    @Override
    public void save(User u) {
        u.setPassword(u.getPassword());

        uRepo.save(u);
    }

    @Override
    public User findByUsername(String username) {
        return uRepo.findByUsername(username);
    }

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) uRepo.findAll();
    }
}
