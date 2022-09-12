package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.Enum.ParkingSpotQuota;
import com.dhana.parkinglots.Enum.ParkingSpotStatus;
import com.dhana.parkinglots.entity.ParkingFloor;
import com.dhana.parkinglots.entity.ParkingSpot;
import com.dhana.parkinglots.entity.ParkingSpotType;
import com.dhana.parkinglots.repositary.ParkingSpotRepo;
import com.dhana.parkinglots.service.ParkingFloorService;
import com.dhana.parkinglots.service.ParkingSpotService;
import com.dhana.parkinglots.service.ParkingSpotTypeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {
    @Autowired
    private ParkingSpotRepo parkingSpotRepo;
    @Autowired
    private ParkingFloorService parkingFloorService;
    @Autowired
    private ParkingSpotTypeService parkingSpotTypeService;

    @Override
    public Set<ParkingSpot> getAll() {
        return parkingSpotRepo.findAllBy();
    }

    @Override
    public ParkingSpot getById(int id) {
        return parkingSpotRepo.findById(id).get();
    }

    @Override
    @Transactional
    public ParkingSpot create(@NotNull Map<String, Object> parkingSpotInput) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setParkingSpotNo(Integer.parseInt((String) parkingSpotInput.get("parkingSpotNo")));
        ParkingFloor parkingFloor = parkingFloorService.getByFloorNoAndParkingLotId(Integer.parseInt((String) parkingSpotInput.get("parkingFloorNo")), (String) parkingSpotInput.get("parkingLotId"));
        if (parkingFloor != null) {
            parkingSpot.setParkingFloor(parkingFloor);
        } else {
            throw new RuntimeException("the Floor not found");
        }
        ParkingSpotType parkingSpotType = parkingSpotTypeService.getById((String) parkingSpotInput.get("parkingSpotType"));
        if (parkingSpotType != null) {
            parkingSpot.setParkingSpotType(parkingSpotType);
        } else {
            throw new RuntimeException("parkingspot type not found");
        }
        parkingSpot.setParkingSpotQuota(ParkingSpotQuota.valueOf(parkingSpotInput.get("parkingSpotQuota").toString()));
        parkingSpot.setStatus(ParkingSpotStatus.valueOf(parkingSpotInput.get("status").toString()));
        if (parkingSpotRepo.findByParkingSpotNoAndParkingSpotTypeAndParkingFloorAndParkingSpotQuota(parkingSpot.getParkingSpotNo(), parkingSpot.getParkingSpotType(), parkingSpot.getParkingFloor(),parkingSpot.getParkingSpotQuota()) == null) {
            return parkingSpotRepo.save(parkingSpot);
        } else {
            throw new RuntimeException("parkingSpot already exists");
        }


    }

    @Override
    public void delete(Map<String, Object> parkingSpotDelete) {
        ParkingSpot parkingSpot=new ParkingSpot();
        parkingSpot.setParkingSpotNo(Integer.parseInt(parkingSpotDelete.get("parkingSpotNo").toString()));
        ParkingFloor parkingFloor=parkingFloorService.getByFloorNoAndParkingLotId(Integer.parseInt(parkingSpotDelete.get("parkingFloorNo").toString()), parkingSpotDelete.get("parkingLotId").toString());
        if(parkingFloor!=null){
            parkingSpot.setParkingFloor(parkingFloor);
        }else {
            throw new RuntimeException("ParkingFloor not exist");
        }
        ParkingSpotType parkingSpotType=parkingSpotTypeService.getById(parkingSpotDelete.get("parkingSpotType").toString());
        if(parkingSpotType!=null){
            parkingSpot.setParkingSpotType(parkingSpotType);
        }else{
            throw new RuntimeException("ParkingSpotType not exist");
        }
        parkingSpot.setParkingSpotQuota(ParkingSpotQuota.valueOf(parkingSpotDelete.get("parkingSpotQuota").toString()));
        parkingSpot=parkingSpotRepo.findByParkingSpotNoAndParkingSpotTypeAndParkingFloorAndParkingSpotQuota(parkingSpot.getParkingSpotNo(), parkingSpot.getParkingSpotType(), parkingSpot.getParkingFloor(),parkingSpot.getParkingSpotQuota());
        if(parkingSpot!=null){
            parkingSpotRepo.deleteById(parkingSpot.getId());
        }else{
            throw new RuntimeException("ParkingSpot not exist");
        }


    }

    @Override
    public List<ParkingSpot> getAvailableSpots(ParkingSpotType parkingSpotType,ParkingSpotQuota parkingSpotQuota,ParkingSpotStatus available) {
        List<ParkingSpot> parkingSpots=parkingSpotRepo.findByParkingSpotTypeAndParkingSpotQuotaAndStatusOrderByParkingSpotNoAsc(parkingSpotType,parkingSpotQuota,available);
        return  parkingSpots;
    }

    @Override
    public ParkingSpot getSpot(ParkingSpotType parkingSpotType,ParkingSpotQuota parkingSpotQuota) {
        List<ParkingSpot> parkingSpots=this.getAvailableSpots(parkingSpotType,parkingSpotQuota,ParkingSpotStatus.AVAILABLE);
        if(parkingSpots.isEmpty()){
            return null;
        }
        return parkingSpots.get(0);
    }

    @Override
    public ParkingSpot update(ParkingSpot parkingSpot){

        return parkingSpotRepo.save(parkingSpot);
    }


}
