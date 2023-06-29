package edu.nechaev.project.services.impl;

import edu.nechaev.project.dto.Member;
import edu.nechaev.project.dto.Subscription;
import edu.nechaev.project.dto.SubscriptionOption;
import edu.nechaev.project.repositories.SubscriptionOptionRepository;
import edu.nechaev.project.repositories.SubscriptionRepository;
import edu.nechaev.project.services.MemberService;
import edu.nechaev.project.services.SubscriptionService;
import edu.nechaev.project.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionOptionRepository subscriptionOptionRepository;
    private SubscriptionRepository subscriptionRepository;
    private MemberService memberServiceImpl;
    public Iterable<SubscriptionOption> getAll() {
        return subscriptionOptionRepository.findAll();
    }

    public boolean buySubscription(long subId, long memberId, String cardNumber, String expirationDate, String cardholderName, String cvc) {
        Member member = memberServiceImpl.findById(memberId);
        if (!Utils.isCurrentUser(member.getEmail())) {
            throw new RuntimeException("Доступ запрещён");
        }
        if (member.getExpirationDate() != null && member.getExpirationDate().after(new Timestamp(System.currentTimeMillis()))) throw new RuntimeException("У вас уже есть абонемент");
        if (!Utils.validateCreditCard(cardNumber, expirationDate, cardholderName, cvc)) throw new RuntimeException("Ошибка при оплате. Неверные данные карты");
        Subscription subscription = new Subscription();
        subscription.setOption(subId);
        subscription.setMember(memberId);
        subscription.setDate(new Date(System.currentTimeMillis()));
        subscriptionRepository.save(subscription);
        return true;
    }
}
