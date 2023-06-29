package edu.nechaev.project.dto;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("subscription_option")
public class SubscriptionOption {
    private long id;
    private String name;
    private short duration, price;
    private boolean isActive;
}
