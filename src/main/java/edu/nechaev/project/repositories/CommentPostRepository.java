package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.CommentPost;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface CommentPostRepository extends CrudRepository<CommentPost, Integer> {
    @Modifying
    @Query("UPDATE comment SET text = :text, time = :time, is_changed = :isChanged WHERE id = :id")
    int updateCommentById(int id, String text, Timestamp time, boolean isChanged);
}
