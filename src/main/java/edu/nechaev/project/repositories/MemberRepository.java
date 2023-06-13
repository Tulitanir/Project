package edu.nechaev.project.repositories;

import edu.nechaev.project.models.Member;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJdbcRepositories
public interface MemberRepository extends CrudRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
}
