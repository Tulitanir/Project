package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.Comment;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query("SELECT * FROM view_comments WHERE news_id = :newsId ORDER BY time DESC")
    Iterable<Comment> findByNewsId(long newsId);
}
