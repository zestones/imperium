package com.imperium.imperium.model;

import java.beans.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    private String lastname, firstname, password;
    private String username, email, phoneNumber;
    private String jobtitle, company, bio;

    private String github, linkedin, twitter, stackOverflow;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] avatar;

    @OneToMany(mappedBy = "following")
    private List<Followers> following;

    @OneToMany(mappedBy = "follower")
    private List<Followers> followers;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Transient
    public String getUserAvatar() {
        return "/home/profile/user/" + getId() + "/avatar";
    }
}
