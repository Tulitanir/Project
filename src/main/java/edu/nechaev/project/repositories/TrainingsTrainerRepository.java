package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.TrainingsTrainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingsTrainerRepository extends CrudRepository<TrainingsTrainer,Integer> {
}
