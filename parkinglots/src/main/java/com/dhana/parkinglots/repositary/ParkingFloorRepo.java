package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.entity.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface ParkingFloorRepo extends JpaRepository<ParkingFloor,Integer> {
    ParkingFloor getById(String id);

    Set<ParkingFloor> findAllBy();

    @Modifying
    @Transactional
    @Query("delete from ParkingFloor where id=:id")
    void deleteById(int id);

    ParkingFloor findByFloorNoAndParkingLotId(int floorNo,String parkingLotId);
}
