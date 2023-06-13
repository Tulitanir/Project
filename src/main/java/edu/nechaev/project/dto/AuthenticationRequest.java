package edu.nechaev.project.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email, password;
}
