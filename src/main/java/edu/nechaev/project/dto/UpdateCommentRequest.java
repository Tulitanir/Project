package edu.nechaev.project.dto;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private long id;
    private String text;
}
