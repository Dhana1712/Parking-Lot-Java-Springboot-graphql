package com.dhana.parkinglots.service;

import com.dhana.parkinglots.entity.Ticket;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface TicketService {

    Ticket getbyId(int id);
    Set<Ticket> getAll();
    Ticket park(Map<String, Object> parkInput);

    Ticket unPark(Map<String, Integer> unParkInput);

    float hours(LocalDateTime from,LocalDateTime to);

    float fare(float totalHours,Ticket ticket);

    Ticket update(Ticket ticket);
}
