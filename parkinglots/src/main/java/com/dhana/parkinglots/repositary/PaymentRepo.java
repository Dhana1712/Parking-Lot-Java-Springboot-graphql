package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Integer> {
}
