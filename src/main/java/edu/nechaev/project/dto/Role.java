package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("role")
public class Role {
    @Id
    private int id;
    private String name;
}
