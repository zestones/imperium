package com.imperium.imperium.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "job", nullable = false)
    private String job;
    @Column(name = "zipcode", nullable = false)
    private String zipcode;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phonenumber", nullable = false)
    private String phonenumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id_user", nullable = false, unique = true)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public User(String lastname, String firstname, String job, String zipcode, String email, String phonenumber) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.job = job;
        this.zipcode = zipcode;
        this.email = email;
        this.phonenumber = phonenumber;
    }
}
