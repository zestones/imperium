package com.imperium.imperium.repository;

import org.springframework.data.repository.CrudRepository;

import com.imperium.imperium.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
