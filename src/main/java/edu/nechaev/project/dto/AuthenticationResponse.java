package edu.nechaev.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private Member member;
    private String accessToken, refreshToken;
    private long expiresIn;
}
