package com.example.personalproject.models;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER, ADMIN, MAINTENANCEWORKER, WAREHOUSEWORKER, QUALITYWORKER, GOODSWORKER;
    @Override
    public String getAuthority()
    {
        return name();
    }
}
