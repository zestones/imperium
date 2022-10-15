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
    // Injecting the repository
    @Autowired
    private UserRepository uRepo;

    @Lazy // We don't need to allocate the memory until it is needed
    @Autowired
    private AuthenticationManager authenticationManager;

    // private final org.slf4j.Logger logger =
    // LoggerFactory.getLogger(UserService.class);
    public final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /************************
     * Start of : Authentification management
     *********************************/
    public Boolean canConnect(User u) {
        User cmp = uRepo.findByUsername(u.getUsername());
        return (isUserRegistered(u) && arePasswordMatching(u.getPassword(), cmp.getPassword()));
    }

    public Boolean canUpdate(User u, User current) {
        return u.getUsername().equals(current.getUsername()) || !isUserRegistered(u);
    }

    public Boolean canUpdatePassword(User u, String pwd1, String pwd2) {
        return !u.getPassword().equals("") && pwd1.equals(pwd2)
                && arePasswordMatching(u.getPassword(), findById(u.getId()).getPassword());
    }

    private Boolean arePasswordMatching(String pwd1, String pwd2) {
        return encoder.matches(pwd1, pwd2);
    }

    public Boolean isUserRegistered(User u) {
        return (uRepo.findByUsername(u.getUsername()) != null);
    }

    @Override
    public void autologin(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());

        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (auth.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public void autologout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = uRepo.findByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), u.getRoles());
    }

    /********************************
     * End of : Authentification management
     ********************************/

    // ??
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

    public String encodePassword(String rawPwd) {
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

    @Override
    public void update(User u) {
        uRepo.save(u);
    }
}
