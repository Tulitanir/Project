package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.News;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    @Override
    @Query("SELECT * FROM news ORDER BY date DESC")
    Iterable<News> findAll();
}
