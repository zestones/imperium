package com.imperium.imperium.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Followers {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_following")
    private User following;

    @ManyToOne
    @JoinColumn(name = "id_follower")
    private User follower;

    public Followers(User from, User to) {
        following = from;
        follower = to;
    }
}
