package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.Program;
import edu.nechaev.project.dto.Training;
import edu.nechaev.project.dto.TrainingsTrainer;
import edu.nechaev.project.services.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/program")
@AllArgsConstructor
public class ProgramController {
    private final ProgramService programService;
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Program>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(programService.getAll());
    }

    @PostMapping("/addProgram")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        return ResponseEntity.ok(programService.addProgram(program));
    }

    @GetMapping("/getTrainings")
    public ResponseEntity<Iterable<TrainingsTrainer>> getTrainings() {
        return ResponseEntity.ok(programService.getTrainings());
    }

    @PostMapping("/addTraining")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Training> addTraining(@RequestBody Training training) {
        return ResponseEntity.ok(programService.addTraining(training));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(illegalArgumentException.getLocalizedMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handle(AccessDeniedException illegalArgumentException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(illegalArgumentException.getLocalizedMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> habdle(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getLocalizedMessage());
    }
}
