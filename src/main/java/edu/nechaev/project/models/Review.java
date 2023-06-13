package edu.nechaev.project.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.sql.Timestamp;

@Table
@Data
public class Review {
    @Id
    private int id;
    private int memberId, programId;
    private int mark;
    private Timestamp date;
    private String text;
    private boolean isEdited;
}
