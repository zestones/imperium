package com.imperium.imperium.service.access;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Access;
import com.imperium.imperium.repository.AccessRepository;
import com.imperium.imperium.repository.UserRepository;

@Service
public class AccessService implements IAccessService {
    @Autowired
    AccessRepository aRepo;

    @Autowired
    UserRepository uRepo;

    public Boolean canShareProject(String username, Long id) {
        return (uRepo.findByUsername(username) != null);
    }

    @Override
    public void save(Access a) {
        aRepo.save(a);
    }

    @Override
    public ArrayList<Long> findIdContributorByIdProject(Long id) {
        return aRepo.findIdContributorByIdProject(id);
    }
}
