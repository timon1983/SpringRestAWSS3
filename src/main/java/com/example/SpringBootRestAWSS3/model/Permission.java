package com.example.SpringBootRestAWSS3.model;

public enum Permission {

    USER_READ("user:read"),
    MODER_READ("moder:read"),
    ADMIN_READ("admin:read"),
    UPLOAD("file:upload"),
    DELETE("file:delete"),
    DOWNLOAD("file:download");



    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
