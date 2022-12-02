package com.imperium.imperium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imperium.imperium.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findUserById(Long id);

    @Query("Select u from User u where CONCAT (u.firstname,' ',u.lastname,' ',u.username) LIKE %?1%")
    List<User> search(String keyword);
}
