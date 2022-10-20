package com.imperium.imperium.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.User;
import com.imperium.imperium.repository.UserRepository;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository uRepo;

    @Lazy // Allocate when needed
    @Autowired
    private AuthenticationManager authenticationManager;

    public final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /********************************
     ** TEST USER
     ********************************/

    /**
     * @param u : User object
     * @return Boolean
     */
    public Boolean canConnect(User u) {
        User cmp = uRepo.findByUsername(u.getUsername());
        return (isUserRegistered(u) && arePasswordMatching(u.getPassword(), cmp.getPassword()));
    }

    /**
     * @param u       : User object
     * @param current : current User object
     * @return Boolean
     */
    public Boolean canUpdate(User u, User current) {
        return u.getUsername().equals(current.getUsername()) || !isUserRegistered(u);
    }

    /**
     * @param u    : User object
     * @param pwd1 : the new password
     * @param pwd2 : the confirmation
     * @return Boolean
     */
    public Boolean canUpdatePassword(User u, String pwd1, String pwd2) {
        return !u.getPassword().equals("") && pwd1.equals(pwd2)
                && arePasswordMatching(u.getPassword(), findById(u.getId()).getPassword());
    }

    /**
     * @param pwd1 : password
     * @param pwd2 : password
     * @return Boolean
     */
    private Boolean arePasswordMatching(String pwd1, String pwd2) {
        return encoder.matches(pwd1, pwd2);
    }

    /**
     * @param u : User object
     * @return Boolean
     */
    public Boolean isUserRegistered(User u) {
        return (uRepo.findByUsername(u.getUsername()) != null);
    }

    /********************************
     ** FIND IN DB
     ********************************/

    /**
     * @return @return ArrayList<User>
     */
    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) uRepo.findAll();
    }

    /**
     * @param id : User id property
     * @return User
     */
    @Override
    public User findById(Long id) {
        return uRepo.findUserById(id);
    }

    /**
     * @param rawPwd : the raw password
     * @return String
     */
    public String encodePassword(String rawPwd) {
        return encoder.encode(rawPwd);
    }

    /**
     * @param username : User username property
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        return uRepo.findByUsername(username);
    }

    /********************************
     ** UPDATE DB
     ********************************/

    /**
     * @param u : User object
     */
    @Override
    public void save(User u) {
        u.setPassword(encodePassword(u.getPassword()));
        uRepo.save(u);
    }

    /**
     * @param u : User object
     */
    @Override
    public void update(User u) {
        uRepo.save(u);
    }

    /********************************
     ** PROCESS DATA LOCALLY
     ********************************/
    /**
     * 
     * @param arrId : Array of User id
     *              Get ArrayList of User from an array list of id User
     * @return ArrayList<User>
     */
    public ArrayList<User> getArrayUserByArrayidUser(ArrayList<Long> arrId) {
        ArrayList<User> arrUser = new ArrayList<>();
        for (Long id : arrId)
            arrUser.add(findById(id));
        return arrUser;
    }

    /********************************
     ** SESSION MANAGEMENT
     ********************************/

    /**
     * @param username : The username
     * @param password : The password
     */
    @Override
    public void autologin(String username, String password) {
        // load the user
        UserDetails userDetails = loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities());

        // Attempts to authenticate the passed Authentication object
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Authenticated the user if possible
        if (auth.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(auth);
    }

    /**
     * @param request  : provide request information for HTTP servlets
     * @param response : provide HTTP-specific functionality in sending
     */
    @Override
    public void autologout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Performs a logout
        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
    }

    /**
     * @param username : The username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = uRepo.findByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), u.getRoles());
    }
}
