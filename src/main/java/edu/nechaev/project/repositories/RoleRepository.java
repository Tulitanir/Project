package edu.nechaev.project.repositories;

import edu.nechaev.project.dto.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
