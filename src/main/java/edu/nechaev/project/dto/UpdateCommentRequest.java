package edu.nechaev.project.dto;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private int id;
    private String text;
}
