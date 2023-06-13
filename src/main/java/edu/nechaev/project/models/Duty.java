package edu.nechaev.project.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Time;
import java.sql.Timestamp;

@Table
@Data
public class Duty {
    @Id
    private int id;
    private int placeId;
    private Timestamp start, end;
}
