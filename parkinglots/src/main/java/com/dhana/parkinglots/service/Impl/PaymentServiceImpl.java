package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.Enum.ParkingSpotQuota;
import com.dhana.parkinglots.Enum.PaymentMethod;
import com.dhana.parkinglots.Enum.PaymentStatus;
import com.dhana.parkinglots.entity.Payment;
import com.dhana.parkinglots.entity.Ticket;
import com.dhana.parkinglots.repositary.PaymentRepo;
import com.dhana.parkinglots.service.PaymentService;
import com.dhana.parkinglots.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private TicketService ticketService;
    @Override
    @Transactional
    public Payment cardPayment(int ticketId) {
        Ticket ticket=ticketService.getbyId(ticketId);

        if(ticket.getPayment()==null) {
            Payment payment=new Payment();
            payment.setMethod(PaymentMethod.CARD);
            payment.setTicket(ticket);
            if (ticket.getParkingSpot().getParkingSpotQuota() == ParkingSpotQuota.ELECTRIC) {
                payment.setAmount(ticket.getParkingFare() + ticket.getElectricBill().getEnergyConsumptionCost());
            }else {
                payment.setAmount(ticket.getParkingFare());
            }
            if (this.cardPayment(payment)) {
                payment.setStatus(PaymentStatus.SUCCESS);
                payment.setPaidAt(LocalDateTime.now());
                ticket.setPayment(payment);
                payment = paymentRepo.save(payment);
                ticketService.update(ticket);
                return payment;
            } else {
                throw new RuntimeException("Payment Failed");
            }
        }else{
            throw new RuntimeException("payment already compleated");
        }
    }

    @Override
    @Transactional
    public Payment cashPayment(int ticketId) {
        Ticket ticket=ticketService.getbyId(ticketId);
        if(ticket.getPayment() == null) {
            Payment payment = new Payment();
            payment.setMethod(PaymentMethod.CASH);
            payment.setPaidAt(LocalDateTime.now());
            payment.setTicket(ticket);
            payment.setStatus(PaymentStatus.SUCCESS);
            if (ticket.getParkingSpot().getParkingSpotQuota() == ParkingSpotQuota.ELECTRIC) {
                payment.setAmount(ticket.getParkingFare()+ticket.getElectricBill().getEnergyConsumptionCost());
            }else {
                payment.setAmount(ticket.getParkingFare());
            }
            ticket.setPayment(payment);
            payment = paymentRepo.save(payment);
            ticketService.update(ticket);
            return payment;
        }else{
            throw new RuntimeException("payment already compleated");
        }
    }


    private boolean cardPayment(Payment payment) {
        return true;
    }
}
