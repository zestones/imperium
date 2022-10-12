package com.imperium.imperium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imperium.imperium.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // @Modifying
    // @Query("update users u set u.firstname = ?1 , u.lastname = ?2 , u.job = ?3 ,
    // u.email = ?4 , u.zipcode = ?5 , u.phonenumber = ?6 where u.id = ?7")
    // int setfields(String firstname, String lastname, String job, String email,
    // String zipcode, String phonenumber,
    // Long id);

    User findByUsername(String username);

    User findUserById(Long id);

}
