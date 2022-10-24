package com.imperium.imperium.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imperium.imperium.model.Followers;
import com.imperium.imperium.model.User;
import com.imperium.imperium.service.followers.FollowersService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class FollowersController {

    @Autowired
    private UserService service;

    @Autowired
    private FollowersService followersService;

    /**
     * @param username : username of the User
     * @return String : redirect to the PageController
     */
    @PostMapping(value = "/home/profile/{username}/follow")
    private String followUser(@PathVariable String username) {
        User u = service.findByUsername(username);

        followersService.save(new Followers(UserController.getCurrentUser(), u));

        return "redirect:/home/profile/{username}";
    }

    /**
     * @param currentUsername : username of the profile page (PathVariable)
     * @param username        : username of the targer follow (PathVariable)
     * @param tab             : tab name (Follower or Following page)
     * @return String : redirect to the PageController
     */
    @PostMapping(value = "/home/profile/{currentUsername}/follow/{username}/{tab}")
    private String followPageUser(@PathVariable String currentUsername, @PathVariable String username,
            @PathVariable String tab) {
        User u = service.findByUsername(username);

        followersService.save(new Followers(UserController.getCurrentUser(), u));

        return "redirect:/home/profile/{currentUsername}/" + tab.toLowerCase();
    }

    /**
     * @param currentUsername : username of the profile page (PathVariable)
     * @param username        : username of the targer follow (PathVariable)
     * @param tab             : tab name (Follower or Following page)
     * @return String : redirect to the PageController
     */
    @PostMapping(value = "/home/profile/{currentUsername}/unfollow/{username}/{tab}")
    private String unfollowPageUser(@PathVariable String currentUsername, @PathVariable String username,
            @PathVariable String tab) {

        User u = service.findByUsername(username);
        Followers f = followersService.findByIdFollowersAndIdFollowing(u.getId(),
                UserController.getCurrentUser().getId());

        followersService.delete(f);

        return "redirect:/home/profile/{currentUsername}/" + tab.toLowerCase();
    }

    /**
     * @param username : username of the profile page Owner (PathVariable)
     * @return String : redirect to the PageController
     */
    @PostMapping(value = "/home/profile/{username}/unfollow")
    private String unfollowUser(@PathVariable String username) {

        User u = service.findByUsername(username);
        Followers f = followersService.findByIdFollowersAndIdFollowing(u.getId(),
                UserController.getCurrentUser().getId());

        followersService.delete(f);

        return "redirect:/home/profile/{username}";
    }
}
