package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.entity.ParkingSpotType;
import com.dhana.parkinglots.repositary.ParkingSpotTypeRepo;
import com.dhana.parkinglots.service.ParkingSpotTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Service
public class ParkingSpotTypeServiceImpl implements ParkingSpotTypeService {

    @Autowired
    private ParkingSpotTypeRepo parkingSpotTypeRepo;

    @Override
    @Transactional
    public Set<ParkingSpotType> getAll() {
        return parkingSpotTypeRepo.findAllBy();
    }

    @Override
    @Transactional
    public ParkingSpotType getById(String id) {

        return parkingSpotTypeRepo.findById(id).get();
    }

    @Override
    @Transactional
    public ParkingSpotType create(ParkingSpotType parkingSpotType) {

        return parkingSpotTypeRepo.save(parkingSpotType);
    }

    @Override
    @Transactional
    public void delete(String id) {
        parkingSpotTypeRepo.deleteById(id);

    }
}
