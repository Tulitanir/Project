package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.Training;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long> {
    @Query("SELECT * FROM get_training_by_trainer_date(:trainerId, :dateTrain, :beginningTime, :endTime)")
    List<Training> findBetween(
            @Param("trainerId") long trainerName,
            @Param("dateTrain") LocalDate trainingDate,
            @Param("beginningTime") LocalTime beginningTime,
            @Param("endTime") LocalTime endTime
    );

}
