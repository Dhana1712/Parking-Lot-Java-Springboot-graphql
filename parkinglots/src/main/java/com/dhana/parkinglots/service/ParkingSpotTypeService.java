package com.dhana.parkinglots.service;

import com.dhana.parkinglots.entity.ParkingLot;
import com.dhana.parkinglots.entity.ParkingSpotType;

import java.util.Set;

public interface ParkingSpotTypeService {
    Set<ParkingSpotType> getAll();
    ParkingSpotType getById(String id);
    ParkingSpotType create(ParkingSpotType parkingSpotType);
    void delete(String id);
}
