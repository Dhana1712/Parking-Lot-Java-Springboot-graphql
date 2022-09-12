package com.dhana.parkinglots.service.Impl;

import com.dhana.parkinglots.Enum.PanelType;
import com.dhana.parkinglots.entity.EntryExitPanel;
import com.dhana.parkinglots.entity.ParkingLot;
import com.dhana.parkinglots.repositary.EntryExitPanelRepo;
import com.dhana.parkinglots.service.EntryExitPanelService;
import com.dhana.parkinglots.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class EntryExitPanelServiceImpl implements EntryExitPanelService {

    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private EntryExitPanelRepo entryExitPanelRepo;
    @Override
    public Set<EntryExitPanel> getAll() {

        return entryExitPanelRepo.findAllBy();
    }

    @Override
    public EntryExitPanel getById(int id) {
        return entryExitPanelRepo.findById(id).get();
    }

    @Override
    public EntryExitPanel create(Map<String, Object> panelInput) {
        EntryExitPanel entryExitPanel=new EntryExitPanel();
        ParkingLot parkingLot=parkingLotService.getById((String) panelInput.get("parkingLotId"));
        if(parkingLot!=null){
            entryExitPanel.setParkingLot(parkingLot);
        }else{
            throw new RuntimeException("ParkingLot not found");
        }
        entryExitPanel.setPanelName((String) panelInput.get("panelName"));
        entryExitPanel.setPanelType(PanelType.valueOf(panelInput.get("panelType").toString()));
        if(entryExitPanelRepo.findByPanelNameAndPanelTypeAndParkingLot(entryExitPanel.getPanelName(),entryExitPanel.getPanelType(),entryExitPanel.getParkingLot())==null){
            return entryExitPanelRepo.save(entryExitPanel);
        }else{
            throw new RuntimeException("Panel already exists");
        }
    }

    @Override
    public void delete(Map<String,Object> panelInput) {
        EntryExitPanel entryExitPanel=new EntryExitPanel();
        ParkingLot parkingLot=parkingLotService.getById(panelInput.get("parkingLotId").toString());
        if(parkingLot!=null){
            entryExitPanel.setParkingLot(parkingLot);
        }else{
            throw new RuntimeException("ParkingLot not found");
        }
        entryExitPanel.setPanelName(panelInput.get("panelName").toString());
        entryExitPanel.setPanelType(PanelType.valueOf(panelInput.get("panelType").toString()));
        entryExitPanel=entryExitPanelRepo.findByPanelNameAndPanelTypeAndParkingLot(entryExitPanel.getPanelName(),entryExitPanel.getPanelType(),entryExitPanel.getParkingLot());
        if(entryExitPanel!=null){
            entryExitPanelRepo.delete(entryExitPanel);
        }else{
            throw new RuntimeException("Panel not exists");
        }
    }
}
