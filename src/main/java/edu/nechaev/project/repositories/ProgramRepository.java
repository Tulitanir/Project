package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Long> {
}
