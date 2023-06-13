package edu.nechaev.project.repositories;

import edu.nechaev.project.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findByNewsId(int newsId);
}
