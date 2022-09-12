package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.entity.ParkingSpotType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ParkingSpotTypeRepo extends JpaRepository<ParkingSpotType,String> {
    Set<ParkingSpotType> findAllBy();
}
