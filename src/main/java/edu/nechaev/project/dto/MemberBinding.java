package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class MemberBinding {
    private int member, training;
}
