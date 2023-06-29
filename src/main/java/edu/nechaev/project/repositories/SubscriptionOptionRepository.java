package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.SubscriptionOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionOptionRepository extends CrudRepository<SubscriptionOption, Long> {
}
