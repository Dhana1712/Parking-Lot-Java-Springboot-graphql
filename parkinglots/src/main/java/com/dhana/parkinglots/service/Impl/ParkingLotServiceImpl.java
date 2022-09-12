package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.entity.ParkingLot;
import com.dhana.parkinglots.repositary.ParkingLotRepo;
import com.dhana.parkinglots.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepo parkingLotRepo;

    @Override
    public Set<ParkingLot> getAll() {
        return parkingLotRepo.findAllBy();
    }

    @Override
    @Transactional
    public ParkingLot getById(String id) {
        return parkingLotRepo.findById(id).get();
    }

    @Override
    public ParkingLot create(ParkingLot parkingLot) {

        return parkingLotRepo.save(parkingLot);
    }

    @Override
    public void delete(String id) {

        parkingLotRepo.deleteById(id);
    }
}
