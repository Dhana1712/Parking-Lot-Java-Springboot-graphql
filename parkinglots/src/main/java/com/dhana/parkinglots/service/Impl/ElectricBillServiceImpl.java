package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.Enum.ElectricStatus;
import com.dhana.parkinglots.entity.ElectricBill;
import com.dhana.parkinglots.entity.Ticket;
import com.dhana.parkinglots.repositary.ElectricBillRepo;
import com.dhana.parkinglots.service.ElectricBillService;
import com.dhana.parkinglots.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class ElectricBillServiceImpl implements ElectricBillService {
    @Autowired
    private ElectricBillRepo electricBillRepo;
    @Autowired
    private TicketService ticketService;
    @Override
    public ElectricBill powerOn(int ticketId) {
        Ticket ticket=ticketService.getbyId(ticketId);
        ElectricBill electricBill=new ElectricBill();
        electricBill.setStatus(ElectricStatus.POWERON);
        ticket.setElectricBill(electricBill);
        ElectricBill electricBill1=electricBillRepo.save(electricBill);
        ticketService.update(ticket);
        return electricBill1;
    }

    @Override
    public ElectricBill getById(int id) {
        return electricBillRepo.findById(id).get();
    }

    @Override
    public ElectricBill powerOff(Map<String,String> powerOffInput) {
        Ticket ticket=ticketService.getbyId(Integer.parseInt(powerOffInput.get("ticketId")));
        ElectricBill electricBill=ticket.getElectricBill();
        electricBill.setStatus(ElectricStatus.POWEROFF);
        electricBill.setEnergyConsumptionCost(Float.parseFloat(powerOffInput.get("energyConsumptionCost")));
        return electricBillRepo.save(electricBill);
    }
}
