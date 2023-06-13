package edu.nechaev.project.repositories;


import edu.nechaev.project.dto.RefreshStorage;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RefreshStorageRepository extends KeyValueRepository<RefreshStorage, String> {
}
