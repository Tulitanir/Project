package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.AuthenticationRequest;
import edu.nechaev.project.dto.AuthenticationResponse;
import edu.nechaev.project.models.Member;
import edu.nechaev.project.services.AuthenticationService;
import edu.nechaev.project.services.MemberService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestPart("name") String name,
                                           @RequestPart("surname") String surname,
                                           @RequestPart("phone") String phone,
                                           @RequestPart("email") String email,
                                           @RequestPart("password") String password,
                                           @RequestPart("image") @Nullable MultipartFile image) {
        return ResponseEntity.ok(
                authenticationService.register(
                        Member.builder()
                                .name(name)
                                .surname(surname)
                                .phone(phone)
                                .email(email)
                                .password(password)
                                .build(),
                        image)
        );
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handle(AuthenticationException authenticationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticationException.getLocalizedMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException authenticationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticationException.getLocalizedMessage());
    }
}
