package com.uber.services;

import com.uber.model.Payment;
import com.uber.model.Ride;
import com.uber.model.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
