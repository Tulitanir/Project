package edu.nechaev.project.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class SubscriptionOptions {
    @Id
    private int id;
    private String name, description;
    private int date, discount, price;
}
