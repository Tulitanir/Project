package edu.nechaev.project.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessToken {
    private String token;
    private long expiresIn;
}
