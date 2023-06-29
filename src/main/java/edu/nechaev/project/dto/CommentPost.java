package edu.nechaev.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Table("comment")
public class CommentPost {
    @Id
    private long id;
    private long memberId, newsId;
    private String text;
    private boolean isChanged;
    @JsonIgnore
    private Timestamp time;
}
