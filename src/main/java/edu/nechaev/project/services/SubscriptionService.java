package edu.nechaev.project.services;

import edu.nechaev.project.dto.SubscriptionOption;

public interface SubscriptionService {
    Iterable<SubscriptionOption> getAll();

    boolean buySubscription(long subId, long memberId, String cardNumber, String expirationDate, String cardholderName, String cvc);
}
