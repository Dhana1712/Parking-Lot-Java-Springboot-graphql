package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.entity.ParkingFloor;
import com.dhana.parkinglots.entity.ParkingLot;
import com.dhana.parkinglots.repositary.ParkingFloorRepo;
import com.dhana.parkinglots.service.ParkingFloorService;
import com.dhana.parkinglots.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Service
public class ParkingFloorServiceImpl implements ParkingFloorService {

    @Autowired
    private ParkingFloorRepo parkingFloorRepo;
    @Autowired
    private ParkingLotService parkingLotService;

    @Override
    public Set<ParkingFloor> getAll() {
        return parkingFloorRepo.findAllBy();
    }

    @Override
    public ParkingFloor getById(int id) {
        return parkingFloorRepo.findById(id).get();
    }

    @Override
    @Transactional
    public ParkingFloor create(Map<String,String> parkingFloorInput) throws RuntimeException{
        ParkingFloor parkingFloor = new ParkingFloor();
        ParkingLot parkingLot=parkingLotService.getById(parkingFloorInput.get("parkingLotId"));
        if(parkingLot!=null){
            parkingFloor.setParkingLot(parkingLot);
        }else{
            throw new RuntimeException("PakingLot not exist");
        }
        parkingFloor.setFloorNo(Integer.parseInt(parkingFloorInput.get("floorNo")));
        if(parkingFloorRepo.findByFloorNoAndParkingLotId(parkingFloor.getFloorNo(), parkingFloor.getParkingLot().getId()) == null){
            return parkingFloorRepo.save(parkingFloor);
        }else{
            throw new RuntimeException("Floor already exist");
        }

    }

    @Override
    @Transactional
    public void delete(Map<String,String> parkingFloorInput) {
        ParkingFloor parkingFloor=new ParkingFloor();
        parkingFloor.setFloorNo(Integer.parseInt(parkingFloorInput.get("floorNo")));
        ParkingLot parkingLot=parkingLotService.getById(parkingFloorInput.get("parkingLotId"));
        if(parkingLot!=null){
            parkingFloor.setParkingLot(parkingLot);
        }else{
            throw new RuntimeException("ParkingLot not exist");
        }
        parkingFloor=parkingFloorRepo.findByFloorNoAndParkingLotId(parkingFloor.getFloorNo(),parkingFloor.getParkingLot().getId());
        if(parkingFloor!=null){
            parkingFloorRepo.deleteById(parkingFloor.getId());
            //parkingFloorRepo.delete(parkingFloor);
        }else{
            throw new RuntimeException("ParkingFloor not exist");
        }
    }

    @Override
    public ParkingFloor getByFloorNoAndParkingLotId(int floorNo, String parkingLotId) {
        return parkingFloorRepo.findByFloorNoAndParkingLotId(floorNo,parkingLotId);
    }

}
