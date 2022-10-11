package com.imperium.imperium.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    //Existing roles in the project
    USER,
    MODERATEUR,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
