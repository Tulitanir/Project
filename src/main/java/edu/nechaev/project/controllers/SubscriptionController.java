package edu.nechaev.project.controllers;

import edu.nechaev.project.dto.BuySubRequest;
import edu.nechaev.project.dto.SubscriptionOption;
import edu.nechaev.project.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<SubscriptionOption>> getAll() {
        return ResponseEntity.ok(subscriptionService.getAll());
    }

    @PostMapping("/buySubscription")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> buySubscription(@RequestBody BuySubRequest buySubRequest) {
        System.out.println(buySubRequest);
        return ResponseEntity.ok(subscriptionService.buySubscription(buySubRequest.getSubscriptionId(),
                buySubRequest.getMemberId(),
                buySubRequest.getCardNumber(),
                buySubRequest.getExpirationDate(),
                buySubRequest.getCardholderName(),
                buySubRequest.getCvc()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getLocalizedMessage());
    }
}
