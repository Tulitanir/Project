package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Table
@Data
public class Subscription {
    @Id
    private long id;
    private long member, option;
    private Date date;
}
