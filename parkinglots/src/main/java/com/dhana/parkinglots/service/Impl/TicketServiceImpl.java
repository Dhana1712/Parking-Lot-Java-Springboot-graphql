package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.Enum.ParkingSpotQuota;
import com.dhana.parkinglots.Enum.ParkingSpotStatus;
import com.dhana.parkinglots.entity.*;
import com.dhana.parkinglots.repositary.TicketRepo;
import com.dhana.parkinglots.service.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private ParkingSpotService parkingSpotService;
    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private ParkingSpotTypeService parkingSpotTypeService;
    @Autowired
    private EntryExitPanelService entryExitPanelService;
    @Override
    public Ticket getbyId(int id) {
        return ticketRepo.findById(id).get();
    }

    @Override
    public Set<Ticket> getAll() {
        return null;
    }

    @Transactional
    @Override
    public Ticket park(Map<String, Object> parkInput) {
        Ticket ticket=new Ticket();
        ticket.setVehicleNumber(parkInput.get("vehicleNo").toString());
        EntryExitPanel entryExitPanel=entryExitPanelService.getById(Integer.parseInt(parkInput.get("entryPanelId").toString()));
        ticket.setEntryPanel(entryExitPanel);
        ParkingLot parkingLot=parkingLotService.getById(parkInput.get("parkingLotId").toString());
        ticket.setParkingLot(parkingLot);
        ParkingSpotType parkingSpotType=parkingSpotTypeService.getById(parkInput.get("vehicleType").toString());
        if (parkingSpotType == null){
            throw new RuntimeException("Parking Spot not available for this vehicle type");
        }else {
            ticket.setVehicleType(parkInput.get("vehicleType").toString());
        }
        synchronized (parkingSpotService) {
            ParkingSpot parkingSpot = parkingSpotService.getSpot(parkingSpotType,ParkingSpotQuota.valueOf(parkInput.get("parkingSpotQuota").toString()));
            if (parkingSpot != null) {
                ticket.setParkingSpot(parkingSpot);
                parkingSpot.setStatus(ParkingSpotStatus.OCCUPIED);
                parkingSpotService.update(parkingSpot);
            } else {
                throw new RuntimeException("Parking Spot not available");
            }
            ticket.setEntryTime(LocalDateTime.now());
            return ticketRepo.save(ticket);

        }
    }

    @Override
    @Transactional
    public Ticket unPark(@NotNull Map<String, Integer> unParkInput) {
        Ticket ticket = this.getbyId(unParkInput.get("ticketId"));
        EntryExitPanel exitPanel=entryExitPanelService.getById(unParkInput.get("exitPanelId"));
        if(exitPanel!=null) {
            ticket.setExitPanel(exitPanel);
        }else{
            throw new RuntimeException("Exit Panel Not found");
        }
        ticket.setExitTime(LocalDateTime.now());
        ticket.setParkingHours(this.hours(ticket.getEntryTime(),ticket.getExitTime()));
        ticket.setParkingFare(this.fare(ticket.getParkingHours(),ticket));
        ParkingSpot parkingSpot=ticket.getParkingSpot();
        parkingSpot.setStatus(ParkingSpotStatus.AVAILABLE);
        parkingSpotService.update(parkingSpot);
        return ticketRepo.save(ticket);
    }

    @Override
    public float hours(LocalDateTime from, LocalDateTime to) {
        float hours= ChronoUnit.MINUTES.between(from,to)/(float)60;
        return Float.valueOf(new DecimalFormat("#.#").format(hours));
    }

    @Override
    public float fare(float totalHours,Ticket ticket) {
        ParkingSpotType parkingSpotType=parkingSpotTypeService.getById(ticket.getVehicleType());
        float fare=totalHours*parkingSpotType.getFarePerHour();
        return Float.valueOf(new DecimalFormat("#.##").format(fare));
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepo.save(ticket);
    }
}
