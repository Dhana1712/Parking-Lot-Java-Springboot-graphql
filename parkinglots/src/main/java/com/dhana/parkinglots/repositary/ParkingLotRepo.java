package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ParkingLotRepo extends JpaRepository<ParkingLot,String> {

    Set<ParkingLot> findAllBy();

    ParkingLot getById(String id);
}
