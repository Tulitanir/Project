package edu.nechaev.project.repositories;

import edu.nechaev.project.models.SubscriptionOptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionOptionsRepository extends CrudRepository<SubscriptionOptions, Integer> {
}
