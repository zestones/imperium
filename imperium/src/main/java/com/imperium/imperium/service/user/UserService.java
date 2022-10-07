package com.imperium.imperium.service.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.User;
import com.imperium.imperium.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository uRepo;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public Boolean canConnect(User u) {
        User cmp = uRepo.findByUsername(u.getUsername());
        return (isUserRegistered(u) && arePasswordMatching(u.getPassword(), cmp.getPassword()));
    }

    private Boolean arePasswordMatching(String pwd1, String pwd2) {
        return encoder.matches(pwd1, pwd2);
    }

    public Boolean isUserRegistered(User u) {
        return (uRepo.findByUsername(u.getUsername()) != null);
    }

    public ArrayList<User> getArrayUserByArrayidUser(ArrayList<Long> arrId) {
        ArrayList<User> arrUser = new ArrayList<>();

        for (Long id : arrId)
            arrUser.add(findById(id));

        return arrUser;
    }

    @Override
    public void save(User u) {
        u.setPassword(encodePassword(u.getPassword()));
        uRepo.save(u);
    }

    @Override
    public User findById(Long id) {
        return uRepo.findUserById(id);
    }

    private String encodePassword(String rawPwd) {
        return encoder.encode(rawPwd);
    }

    @Override
    public User findByUsername(String username) {
        return uRepo.findByUsername(username);
    }

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) uRepo.findAll();
    }
}
