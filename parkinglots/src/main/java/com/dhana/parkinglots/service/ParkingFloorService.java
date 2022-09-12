package com.dhana.parkinglots.service;

import com.dhana.parkinglots.entity.ParkingFloor;
import com.dhana.parkinglots.entity.ParkingLot;

import java.util.Map;
import java.util.Set;

public interface ParkingFloorService {
    Set<ParkingFloor> getAll();

    ParkingFloor getById(int id);

    ParkingFloor create(Map<String, String> parkingFloorInput);

    void delete(Map<String, String> parkingFloorInput);

    ParkingFloor getByFloorNoAndParkingLotId(int floorNo, String parkingLotId);
}
