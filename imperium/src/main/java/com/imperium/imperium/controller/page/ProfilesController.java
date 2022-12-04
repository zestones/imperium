package com.imperium.imperium.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperium.imperium.controller.user.UserController;
import com.imperium.imperium.model.User;
import com.imperium.imperium.service.followers.FollowersService;
import com.imperium.imperium.service.project.ProjectService;
import com.imperium.imperium.service.user.UserService;

@Controller
public class ProfilesController {

    private final String frontendVueJS;

    // Retrieve url value from application properties file
    @Autowired
    ProfilesController(
            @Value("${frontend.http.url}") String frontendVueJS) {
        this.frontendVueJS = frontendVueJS;
    }

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    FollowersService followersService;

    /**
     * @param model    : holder for model attributes
     * @param username : username property (PathVariable)
     * @return String : return the profile file
     */
    @GetMapping(value = "/home/profile/{username}")
    private String profile(Model model, @PathVariable String username) {
        User u = userService.findByUsername(username);

        // USER DATA
        model.addAttribute("user", u);
        model.addAttribute("myProjects", projectService.findProjectByUserId(u.getId()));
        model.addAttribute("owner", username.equals(UserController.getCurrentUser().getUsername()));

        // FOLLOWER / FOLLOWING
        model.addAttribute("numberFollowing", followersService.findIdUserFollowing(u.getId()).size());
        model.addAttribute("numberFollower", followersService.findIdUserFollower(u.getId()).size());
        model.addAttribute("userFollowingList", userService.getArrayUserByArrayidUser(
                followersService.findIdUserFollowing(UserController.getCurrentUser().getId())));

        // return "/user/profile";
        return "redirect:" + frontendVueJS + "?visited=" + u.getId() + "&current="
                + UserController.getCurrentUser().getId();
    }

    /**
     * @param model : holder for model attributes
     * @param error : error (RequestParam)
     * @return String : return the setting file
     */
    @GetMapping(value = "/home/profile/account-settings")
    private String userSetting(Model model, @RequestParam(value = "error", defaultValue = "no-error") String error) {

        // USER DATA
        model.addAttribute("user", UserController.getCurrentUser());

        // PROCESS ERROR MSG
        if (error.equals("password")) {
            model.addAttribute("error", "Passwords are not matching or Wrong previous password!");
        } else if (error.equals("username")) {
            model.addAttribute("error", "Username already used.");
        }

        return "user/settings";
    }

    /**
     * @param model    : holder for model attributes
     * @param username : username property (PathVariable)
     * @return String : return the follows file
     */
    @GetMapping(value = "/home/profile/{username}/follower")
    private String profileFollower(Model model, @PathVariable String username) {
        User u = userService.findByUsername(username);

        // NAV TAB
        model.addAttribute("tab", "Follower");

        // USER DATA
        model.addAttribute("user", u);
        model.addAttribute("currentUser", UserController.getCurrentUser());
        model.addAttribute("userFollowingList", userService.getArrayUserByArrayidUser(
                followersService.findIdUserFollowing(UserController.getCurrentUser().getId())));

        // FOLLOWER
        model.addAttribute("follow",
                userService.getArrayUserByArrayidUser(
                        followersService.findIdUserFollower(u.getId())));

        return "user/follows";
    }

    /**
     * @param model    : holder for model attributes
     * @param username : username property (PathVariable)
     * @return String : return the follows file
     */
    @GetMapping(value = "/home/profile/{username}/following")
    private String profileFollowing(Model model, @PathVariable String username) {
        User u = userService.findByUsername(username);

        // NAV TAB
        model.addAttribute("tab", "Following");

        // USER DATA
        model.addAttribute("user", u);
        model.addAttribute("currentUser", UserController.getCurrentUser());
        model.addAttribute("userFollowingList", userService.getArrayUserByArrayidUser(
                followersService.findIdUserFollowing(UserController.getCurrentUser().getId())));

        // FOLLOWER
        model.addAttribute("follow",
                userService.getArrayUserByArrayidUser(
                        followersService.findIdUserFollowing(u.getId())));

        return "user/follows";
    }
}
