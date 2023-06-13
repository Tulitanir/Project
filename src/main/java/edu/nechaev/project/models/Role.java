package edu.nechaev.project.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("member_role")
@Data
public class Role {
    private String name;
}
