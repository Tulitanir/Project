package edu.nechaev.project.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table
@Data
public class Program {
    @Id
    int id;
    String name, description;
}
