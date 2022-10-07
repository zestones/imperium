package com.imperium.imperium.service.access;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Access;
import com.imperium.imperium.model.User;
import com.imperium.imperium.repository.AccessRepository;
import com.imperium.imperium.repository.ProjectRepository;
import com.imperium.imperium.repository.UserRepository;

@Service
public class AccessService implements IAccessService {
    @Autowired
    AccessRepository aRepo;

    @Autowired
    UserRepository uRepo;

    @Autowired
    ProjectRepository pRepo;

    public Boolean canShareProject(User u, User owner, Long id) {
        return (u != null && !u.equals(owner)
                && !aRepo.findIdContributorByIdProject(id).stream().anyMatch(i -> i.equals(u.getId())));
    }

    @Override
    public void save(Access a) {
        aRepo.save(a);
    }

    @Override
    public ArrayList<Long> findIdContributorByIdProject(Long id) {
        return aRepo.findIdContributorByIdProject(id);
    }

    @Override
    public ArrayList<Long> findIdProjectSharedWithUserId(Long userId) {
        return aRepo.findIdProjectSharedWithUserId(userId);
    }

}
