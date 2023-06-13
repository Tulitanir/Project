package edu.nechaev.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class RoleBinding {
    @Id
    private int id;
    private int member, role;
}
