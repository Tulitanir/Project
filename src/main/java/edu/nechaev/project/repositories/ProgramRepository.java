package edu.nechaev.project.repositories;

import edu.nechaev.project.models.Program;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Integer> {
}
