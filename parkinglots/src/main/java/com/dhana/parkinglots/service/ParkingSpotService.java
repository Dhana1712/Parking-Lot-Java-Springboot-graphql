package com.dhana.parkinglots.service;

import com.dhana.parkinglots.Enum.ParkingSpotQuota;
import com.dhana.parkinglots.Enum.ParkingSpotStatus;
import com.dhana.parkinglots.entity.ParkingSpot;
import com.dhana.parkinglots.entity.ParkingSpotType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ParkingSpotService {
    Set<ParkingSpot> getAll();
    ParkingSpot getById(int id);
    ParkingSpot create(Map<String,Object> parkingSpotInput);

    void delete(Map<String,Object> pakingSpotInput);

    //List<ParkingSpot> getAvailableSpots(ParkingSpotType parkingSpotType, ParkingSpotStatus available);

    List<ParkingSpot> getAvailableSpots(ParkingSpotType parkingSpotType, ParkingSpotQuota parkingSpotQuota, ParkingSpotStatus available);

    ParkingSpot getSpot(ParkingSpotType vehicleType, ParkingSpotQuota parkingSpotQuota);

    ParkingSpot update(ParkingSpot parkingSpot);
}
