package edu.nechaev.project.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Time;
import java.sql.Timestamp;

@Table
@Data
public class PersonalTraining {
    @Id
    private int id;
    private int trainerId, memberId;
    private Timestamp date;
}
