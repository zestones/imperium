package com.imperium.imperium.model;

import java.beans.Transient;
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
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
<<<<<<< imperium/src/main/java/com/imperium/imperium/model/User.java
    private String lastname, firstname, jobtitle, username, password;
=======
    private String lastname, firstname, jobtitle, photo, username, password;
>>>>>>> imperium/src/main/java/com/imperium/imperium/model/User.java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "ImagePhoto")
    private byte[] ImagePhoto;

    @Transient
    public String getPhotosImagePath() {
        return "/home/profile/" + getId();
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

}
