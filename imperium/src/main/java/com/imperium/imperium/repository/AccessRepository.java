package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.imperium.imperium.model.Access;

/* Acces interface Query call for user right read into arraylist contributors  */
public interface AccessRepository extends CrudRepository<Access, Long> {

    @Query(value = "SELECT u.id FROM USERS u, ACCESS a, PROJECTS p WHERE " +
            "a.id_project = :projectId " +
            "AND u.id = a.id_user " +
            "AND a.id_project = p.id " +
            "AND a.CAN_READ = true", nativeQuery = true)
    ArrayList<Long> findIdContributorByIdProject(Long projectId);

    @Query(value = "SELECT p.id FROM PROJECTS p, USERS u, ACCESS a WHERE " +
            "p.id = a.id_project " +
            "AND u.id = a.id_user " +
            "AND p.id_user != :userId " +
            "AND a.id_user = :userId", nativeQuery = true)
    ArrayList<Long> findIdProjectSharedWithUserId(@Param("userId") Long userId);
}
