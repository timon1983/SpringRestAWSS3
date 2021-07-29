package com.example.SpringBootRestAWSS3.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.MODER_READ,Permission.ADMIN_READ)),
    MODERATOR(Set.of(Permission.UPLOAD,Permission.DELETE)),
    ADMIN(Set.of(Permission.USER_READ, Permission.MODER_READ,Permission.ADMIN_READ,
            Permission.UPLOAD,Permission.DELETE,Permission.DOWNLOAD));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
