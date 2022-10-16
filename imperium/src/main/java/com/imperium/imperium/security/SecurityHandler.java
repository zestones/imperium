package com.imperium.imperium.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.imperium.imperium.controller.UserController;
import com.imperium.imperium.service.user.UserService;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Inject
    UserService service;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserController.setCurrentUser(service.findByUsername(authentication.getName()));

        response.sendRedirect("/home");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // TODO : ADD CUSTOM HANDLER

    }
}
