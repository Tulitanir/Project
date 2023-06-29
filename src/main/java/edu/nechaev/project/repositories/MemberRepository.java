package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.Member;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJdbcRepositories
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    @Query("SELECT image FROM member WHERE id = :id")
    Optional<String> findImageById(long id);
}
