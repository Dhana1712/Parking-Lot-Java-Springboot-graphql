package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {
}
