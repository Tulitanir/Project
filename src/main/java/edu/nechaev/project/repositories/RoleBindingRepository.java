package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.RoleBinding;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleBindingRepository extends CrudRepository<RoleBinding, Integer> {
}
