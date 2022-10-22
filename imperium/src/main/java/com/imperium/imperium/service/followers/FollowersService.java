package com.imperium.imperium.service.followers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Followers;
import com.imperium.imperium.repository.FollowersRepository;

@Service
public class FollowersService implements IFollowersService {

    @Autowired
    FollowersRepository fRepo;

    /********************************
     ** FIND IN DB
     ********************************/

    @Override
    public ArrayList<Long> findIdUserFollowing(Long userId) {
        return fRepo.findIdUserFollowing(userId);
    }

    @Override
    public ArrayList<Long> findIdUserFollower(Long userId) {
        return fRepo.findIdUserFollower(userId);
    }

    /********************************
     ** UPDATE DB
     ********************************/

    public void save(Followers f) {
        fRepo.save(f);
    }
}
