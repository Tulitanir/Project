package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table
@Data
public class News {
    @Id
    private long id;
    private String title, text;
    private Timestamp date;
    @Column("is_changed")
    private boolean changed;
}
