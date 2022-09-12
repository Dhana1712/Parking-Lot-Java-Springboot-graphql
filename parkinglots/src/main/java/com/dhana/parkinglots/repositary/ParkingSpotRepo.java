package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.Enum.ParkingSpotQuota;
import com.dhana.parkinglots.Enum.ParkingSpotStatus;
import com.dhana.parkinglots.entity.ParkingFloor;
import com.dhana.parkinglots.entity.ParkingSpot;
import com.dhana.parkinglots.entity.ParkingSpotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface ParkingSpotRepo extends JpaRepository<ParkingSpot,Integer> {
    Set<ParkingSpot> findAllBy();
    @Modifying
    @Transactional
    @Query("delete from ParkingSpot where id=:id")
    void deleteById(int id);
    ParkingSpot findByParkingSpotNoAndParkingSpotTypeAndParkingFloor(int parkingSpotNo, ParkingSpotType parkingSpotType, ParkingFloor parkingFloor);

    //List<ParkingSpot> findByParkingSpotTypeAndStatusOrderByParkingSpotNoAsc(ParkingSpotType parkingSpotType, ParkingSpotStatus available);

    ParkingSpot findByParkingSpotNoAndParkingSpotTypeAndParkingFloorAndParkingSpotQuota(int parkingSpotNo, ParkingSpotType parkingSpotType, ParkingFloor parkingFloor, ParkingSpotQuota parkingSpotQuota);

    List<ParkingSpot> findByParkingSpotTypeAndParkingSpotQuotaAndStatusOrderByParkingSpotNoAsc(ParkingSpotType parkingSpotType, ParkingSpotQuota parkingSpotQuota,ParkingSpotStatus available);
}
