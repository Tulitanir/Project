package edu.nechaev.project.services;

import edu.nechaev.project.models.Program;
import edu.nechaev.project.repositories.ProgramRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;

    public Iterable<Program> getAll() {
        return programRepository.findAll();
    }

    public Program getById(int id) {
        Program program = programRepository.findById(id).orElse(null);
        if (program == null) {
            throw new IllegalArgumentException("Не существует программы тренировок с id " + id);
        }
        return program;
    }
}