package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Table("view_comments")
public class Comment {
    @Id
    private long id;
    private long memberId, newsId;
    private String name, surname, text;
    private Timestamp time;
    private boolean isChanged;
}
