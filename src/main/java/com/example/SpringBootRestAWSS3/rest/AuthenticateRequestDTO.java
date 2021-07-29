package com.example.SpringBootRestAWSS3.rest;

import lombok.Data;

@Data
public class AuthenticateRequestDTO {
    private String email;
    private String password;
}
