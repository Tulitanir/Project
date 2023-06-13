package edu.nechaev.project.repositories;

import edu.nechaev.project.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Integer> {
}
