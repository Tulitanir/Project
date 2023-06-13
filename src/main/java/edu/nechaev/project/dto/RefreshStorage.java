package edu.nechaev.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("RefreshStorage")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshStorage {
    @Id
    private String email;
    private String refreshToken;
}
