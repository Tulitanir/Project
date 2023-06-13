package edu.nechaev.project.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Table
@Data
public class Subscription {
    @Id
    int id;
    int memberId;
    Date expirationDate;
}
