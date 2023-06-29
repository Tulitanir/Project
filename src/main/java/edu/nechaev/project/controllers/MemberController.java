package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.UserRole;
import edu.nechaev.project.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {
    private MemberService memberServiceImpl;
    @GetMapping("/find")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Iterable<UserRole>> findByRole(@RequestParam String role) {
        return ResponseEntity.ok(memberServiceImpl.findByRole(role));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getLocalizedMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handle(AccessDeniedException runtimeException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(runtimeException.getLocalizedMessage());
    }
}
