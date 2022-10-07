package com.imperium.imperium.service.access;

import java.util.ArrayList;

import com.imperium.imperium.model.Access;

public interface IAccessService {

    void save(Access a);

    ArrayList<Long> findIdContributorByIdProject(Long id);

}
