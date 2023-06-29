package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@Table("training_trainer")
public class TrainingsTrainer {
    @Id
    private long id;
    private long trainer, capacity;
    private String name, surname, title;
    @Column("member_count")
    private long memberCount;
    private Date date;
    @Column("beginning")
    private String beg;
    @Column("end")
    private String ending;
}
