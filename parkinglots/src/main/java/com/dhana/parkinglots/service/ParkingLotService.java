package com.dhana.parkinglots.service;

import com.dhana.parkinglots.entity.ParkingLot;

import java.util.Set;

public interface ParkingLotService {

    Set<ParkingLot> getAll();
    ParkingLot getById(String id);
    ParkingLot create(ParkingLot parkingLot);
    void delete(String id);
}
