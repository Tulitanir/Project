package edu.nechaev.project.repositories;

import edu.nechaev.project.models.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer> {
}
