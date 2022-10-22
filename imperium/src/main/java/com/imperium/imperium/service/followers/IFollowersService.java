package com.imperium.imperium.service.followers;

import java.util.ArrayList;

import com.imperium.imperium.model.Followers;

public interface IFollowersService {

    void save(Followers f);

    void delete(Followers f);

    ArrayList<Long> findIdUserFollower(Long userId);

    ArrayList<Long> findIdUserFollowing(Long userId);

    Followers findByIdFollowersAndIdFollowing(Long idFollowers, Long idFollowing);

}
