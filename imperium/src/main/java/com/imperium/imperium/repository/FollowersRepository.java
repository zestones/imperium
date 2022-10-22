package com.imperium.imperium.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imperium.imperium.model.Followers;

public interface FollowersRepository extends CrudRepository<Followers, Long> {
    @Query(value = "SELECT u.id FROM USERS u, FOLLOWERS f " +
            "WHERE u.id = f.id_follower " +
            "AND f.id_following = :userId", nativeQuery = true)
    ArrayList<Long> findIdUserFollowing(Long userId);

    @Query(value = "SELECT u.id FROM USERS u, FOLLOWERS f " +
            "WHERE u.id = f.id_following " +
            "AND f.id_follower = :userId", nativeQuery = true)
    ArrayList<Long> findIdUserFollower(Long userId);

    @Query(value = "SELECT * FROM FOLLOWERS WHERE id_follower = :idFollowers AND id_following = :idFollowing", nativeQuery = true)
    Followers findByIdFollowersAndIdFollowing(Long idFollowers, Long idFollowing);

}
