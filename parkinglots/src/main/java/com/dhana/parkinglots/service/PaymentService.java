package com.dhana.parkinglots.service;

import com.dhana.parkinglots.entity.Payment;

import java.util.Map;

public interface PaymentService {
    Payment cardPayment(int ticketId);
    Payment cashPayment(int ticketId);

}
