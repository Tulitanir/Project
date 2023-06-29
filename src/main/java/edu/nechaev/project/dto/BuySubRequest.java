package edu.nechaev.project.dto;

import lombok.Data;

@Data
public class BuySubRequest {
    private String cardNumber, expirationDate, cardholderName, cvc;
    private long memberId, subscriptionId;
}
