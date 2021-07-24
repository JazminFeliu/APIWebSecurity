package com.example.apiwebsecurity.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.apiwebsecurity.security.UserPermission.*;

public enum UserRole {
    ADMIN(Set.of(USER_WRITE,
            USER_READ,
            PRODUCTO_WRITE,
            PRODUCTO_READ)),
    SELLER(Set.of(USER_READ, USER_WRITE)),
    CLIENTE(Set.of(USER_WRITE,
            USER_READ,
            PRODUCTO_WRITE,
            PRODUCTO_READ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions){
        this.permissions = permissions;
    }


    public Set<UserPermission>getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority>getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permisos = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permisos.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permisos;

    }
}
