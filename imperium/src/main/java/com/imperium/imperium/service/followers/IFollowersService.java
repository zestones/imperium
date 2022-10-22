package com.imperium.imperium.service.followers;

import java.util.ArrayList;

import com.imperium.imperium.model.Followers;

public interface IFollowersService {

    void save(Followers f);

    ArrayList<Long> findIdUserFollowing(Long userId);

    ArrayList<Long> findIdUserFollower(Long userId);
}
