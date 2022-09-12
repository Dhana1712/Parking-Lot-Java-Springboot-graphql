package com.dhana.parkinglots.resolver.parkinglot;

import com.dhana.parkinglots.Enum.ElectricStatus;
import com.dhana.parkinglots.Enum.PanelType;
import com.dhana.parkinglots.Enum.ParkingSpotQuota;
import com.dhana.parkinglots.Enum.ParkingSpotStatus;
import com.dhana.parkinglots.entity.*;
import com.dhana.parkinglots.exception.GraphqlException;
import com.dhana.parkinglots.service.*;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ParkingLotMutation implements GraphQLMutationResolver {

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private ParkingFloorService parkingFloorService;
    @Autowired
    private ParkingSpotTypeService parkingSpotTypeService;
    @Autowired
    private ParkingSpotService parkingSpotService;
    @Autowired
    private EntryExitPanelService entryExitPanelService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;

    @Autowired
    private ElectricBillService electricBillService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ParkingLot createParkingLot(ParkingLot parkingLot){
        try {
            return parkingLotService.create(parkingLot);
        }catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Boolean deleteParkingLot(String id){
       try {
           parkingLotService.delete(id);
           return true;
       }catch (RuntimeException ex){
           throw new GraphqlException(ex);
       }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ParkingFloor addParkingFloor(Map<String,String> parkingFloorInput){
        try {
            return parkingFloorService.create(parkingFloorInput);
        }catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Boolean removeParkingFloor(Map<String,String> parkingFloorInput){
        try {
            parkingFloorService.delete(parkingFloorInput);
            return true;
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ParkingSpotType addParkingSpotType(ParkingSpotType parkingSpotType){
        try {
        return parkingSpotTypeService.create(parkingSpotType);
        }catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Boolean removeParkingSpotType(String parkingSpotType){
       try {
           parkingSpotTypeService.delete(parkingSpotType);
           return true;
       }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ParkingSpot addParkingSpot(Map<String,Object> parkingSpotInput){
        try {
            return parkingSpotService.create(parkingSpotInput);
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public boolean removeParkingSpot(Map<String,Object> parkingSpotDelete){
       try {
           parkingSpotService.delete(parkingSpotDelete);
           return true;
       }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public EntryExitPanel addEntryExitPanel(Map<String,Object> panelInput){
        try {
            return  entryExitPanelService.create(panelInput);
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Boolean removeEntryExitPanel(Map<String,Object> panelInput){
        try {
            entryExitPanelService.delete(panelInput);
            return true;
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARKING_ATTENDANT')")
    public Ticket parkVehicle(Map<String,Object> entryInput){
        try {
            return ticketService.park(entryInput);
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARKING_ATTENDANT')")
    public Ticket unParkVehicle(Map<String,Integer> exitInput){
        try {
            return ticketService.unPark(exitInput);
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARKING_ATTENDANT')")
    public Payment cardPayment(int id){
        try {
            return paymentService.cardPayment(id);
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }
    @PreAuthorize("hasAuthority('ROLE_PARKING_ATTENDANT')")
    public Payment cashPayment(int id){
        try{
            return paymentService.cashPayment(id);
        }
        catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARKING_ATTENDANT')")
    public ElectricBill chargeVehicle(int ticketId){
        try {
            return electricBillService.powerOn(ticketId);
        }catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }

    }
    @PreAuthorize("hasAuthority('ROLE_PARKING_ATTENDANT')")
    public ElectricBill unPlugVehicle(Map<String,String> unPlugInput) {
        try {
            return electricBillService.powerOff(unPlugInput);
        }catch (RuntimeException ex){
            throw new GraphqlException(ex);
        }
    }

    public Boolean nestedInputTypes(ParkingSpotStatus parkingSpotStatus , PanelType panelType, ParkingSpotQuota parkingSpotQuota, ElectricStatus electricStatus){
        return true;
    }

}
