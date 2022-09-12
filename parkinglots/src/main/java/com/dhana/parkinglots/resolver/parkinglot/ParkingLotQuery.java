package com.dhana.parkinglots.resolver.parkinglot;

import com.dhana.parkinglots.entity.ParkingFloor;
import com.dhana.parkinglots.entity.ParkingLot;
import com.dhana.parkinglots.entity.ParkingSpotType;
import com.dhana.parkinglots.entity.Ticket;
import com.dhana.parkinglots.service.ParkingFloorService;
import com.dhana.parkinglots.service.ParkingLotService;
import com.dhana.parkinglots.service.ParkingSpotTypeService;
import com.dhana.parkinglots.service.TicketService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ParkingLotQuery implements GraphQLQueryResolver{
    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private ParkingFloorService parkingFloorService;
    @Autowired
    private ParkingSpotTypeService parkingSpotTypeService;
    @Autowired
    private TicketService ticketService;

    @PreAuthorize("permitAll()")
    public ParkingLot getParkingLot(String id){
        return parkingLotService.getById(id);
    }

    @PreAuthorize("permitAll()")
    public Set<ParkingLot> getAllParkingLot(){
        return parkingLotService.getAll();
    }

    @PreAuthorize("permitAll()")
    public ParkingFloor getParkingFloor(int id){
        return parkingFloorService.getById(id);
    }

    @PreAuthorize("permitAll()")
    public Set<ParkingFloor> getAllParkingFloor(){
        return parkingFloorService.getAll();
    }

    @PreAuthorize("permitAll()")
    public ParkingSpotType getParkingSpotType(String parkingSpotType){
        return parkingSpotTypeService.getById(parkingSpotType);
    }

    @PreAuthorize("permitAll()")
    public Set<ParkingSpotType> getAllParkingSpotType(){
        return parkingSpotTypeService.getAll();
    }

    @PreAuthorize("permitAll()")
    public Ticket getTicket(int ticketId){
        return ticketService.getbyId(ticketId);
    }
}
