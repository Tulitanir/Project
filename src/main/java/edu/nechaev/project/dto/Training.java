package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Data
@Table("trainings")
public class Training {
    @Id
    private int id;
    private int program, trainer, capacity;
    private Date date;
    @Transient
    private String beg, ending;
    private Time beginning, end;
}
