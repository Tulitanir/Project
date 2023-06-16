package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findByNewsId(int newsId);
}
