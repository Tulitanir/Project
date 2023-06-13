package edu.nechaev.project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getLocalizedMessage());
    }
}
