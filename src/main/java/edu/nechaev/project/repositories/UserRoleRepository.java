package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    Iterable<UserRole> findByRole(String role);
}
