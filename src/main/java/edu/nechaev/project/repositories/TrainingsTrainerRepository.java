package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.TrainingsTrainer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface TrainingsTrainerRepository extends CrudRepository<TrainingsTrainer,Long> {
    @Query("SELECT * FROM training_trainer WHERE date >= :date ORDER BY beginning")
    Iterable<TrainingsTrainer> findAllActual(Date date);
    @Query("SELECT * FROM training_trainer WHERE date >= :date AND trainer = :trainer ORDER BY date, beginning")
    Iterable<TrainingsTrainer> findAllByTrainer(long trainer, Date date);

    @Query("SELECT * FROM training_trainer JOIN member_training ON member_training.training = training_trainer.id WHERE date >= :date AND member = :member ORDER BY date, beginning")
    Iterable<TrainingsTrainer> findAllByMember(long member, Date date);
}
