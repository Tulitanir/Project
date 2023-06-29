package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.MemberTraining;
import edu.nechaev.project.dto.TrainingsTrainer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.Optional;

public interface MemberTrainingRepository extends CrudRepository<MemberTraining, Long> {
    @Query("SELECT * FROM member_training WHERE member = :member AND training = :training")
    Optional<MemberTraining> findByTrainingAndMember(long training, long member);
}
