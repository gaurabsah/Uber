package com.uber.strategies.impl;
import org.springframework.stereotype.Service;

import com.uber.model.Driver;
import com.uber.model.Payment;
import com.uber.model.enums.PaymentStatus;
import com.uber.model.enums.TransactionMethod;
import com.uber.repositories.PaymentRepository;
import com.uber.services.WalletService;
import com.uber.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//Rider -> 100
//Driver -> 70 Deduct 30Rs from Driver's wallet

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}

//10 ratingsCount -> 4.0
//new rating 4.6
//updated rating
//new rating 44.6/11 -> 4.05