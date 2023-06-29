package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.*;
import edu.nechaev.project.security.TokenExpiredException;
import edu.nechaev.project.services.AuthenticationService;
import edu.nechaev.project.services.MemberService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationServiceImpl;
    private final MemberService memberServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestPart("name") String name,
                                           @RequestPart("surname") String surname,
                                           @RequestPart("phone") String phone,
                                           @RequestPart("email") String email,
                                           @RequestPart("password") String password,
                                           @RequestPart("image") @Nullable MultipartFile image) {
        return ResponseEntity.ok(
                authenticationServiceImpl.register(
                        new MemberPost(name, surname, phone, email, password),
                        image)
        );
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationServiceImpl.login(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationServiceImpl.refreshToken(request.getRefreshToken()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handle(AuthenticationException authenticationException) {
        authenticationException.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticationException.getLocalizedMessage());
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<String> handle(RedisConnectionFailureException authenticationException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authenticationException.getLocalizedMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handle(TokenExpiredException authenticationException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getLocalizedMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException authenticationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticationException.getLocalizedMessage());
    }
}
