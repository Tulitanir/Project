package edu.nechaev.project.controllers;

import edu.nechaev.project.models.Program;
import edu.nechaev.project.services.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/program")
@AllArgsConstructor
public class ProgramController {
    private final ProgramService programService;
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Program>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(programService.getAll());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(illegalArgumentException.getLocalizedMessage());
    }
}
