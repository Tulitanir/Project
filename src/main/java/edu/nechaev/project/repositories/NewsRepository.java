package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {
}
