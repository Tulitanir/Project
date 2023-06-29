package edu.nechaev.project.dto;

import lombok.Data;

@Data
public class UpdateNewsRequest {
    private long id;
    private String title, text;
}
