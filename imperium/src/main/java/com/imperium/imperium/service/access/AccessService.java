package com.imperium.imperium.service.access;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Access;
import com.imperium.imperium.model.User;
import com.imperium.imperium.repository.AccessRepository;

@Service
public class AccessService implements IAccessService {

    @Autowired
    AccessRepository aRepo;

    /********************************
     ** TEST ACCESS
     ********************************/

    /**
     * @param u     : User object
     * @param owner : User owner of the project
     * @param id    : Project id property
     * @return Boolean
     */
    public Boolean canShareProject(User u, User owner, Long id) {
        return (u != null && !u.equals(owner)
                && !aRepo.findIdContributorByIdProject(id).stream().anyMatch(i -> i.equals(u.getId())));
    }

    /********************************
     ** FIND IN DB
     ********************************/

    /**
     * @param id : Project id property
     * @return ArrayList<Long>
     */
    @Override
    public ArrayList<Long> findIdContributorByIdProject(Long id) {
        return aRepo.findIdContributorByIdProject(id);
    }

    /**
     * @param userId : User id property
     * @return ArrayList<Long>
     */
    @Override
    public ArrayList<Long> findIdProjectSharedWithUserId(Long userId) {
        return aRepo.findIdProjectSharedWithUserId(userId);
    }

    /********************************
     ** UPDATE DB
     ********************************/

    /**
     * @param a : Access object
     */
    @Override
    public void save(Access a) {
        aRepo.save(a);
    }

}
