package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.MemberPost;
import org.springframework.data.repository.CrudRepository;

public interface MemberPostRepository extends CrudRepository<MemberPost, Long> {
}
