package edu.nechaev.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.sql.Timestamp;

@Table
@Data
public class News {
    @Id
    private int id;
    private String title, text;
    private Timestamp date;
}
