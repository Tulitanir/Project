package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.Training;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Integer> {
    @Query("SELECT * FROM trainings WHERE trainer = :trainer AND date = :date AND end > :beginningTime AND beginning < :endTime")
    Training findBetween(int trainer, Date date, Time beginningTime, Time endTime);

}
