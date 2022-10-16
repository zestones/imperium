package com.imperium.imperium.model;

import org.springframework.security.core.GrantedAuthority;

/* Role enum set for SecurityConfig need */
public enum Role implements GrantedAuthority {
    USER,
    MODERATEUR,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
