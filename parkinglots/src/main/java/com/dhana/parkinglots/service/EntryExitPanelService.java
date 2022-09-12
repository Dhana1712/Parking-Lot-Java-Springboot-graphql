package com.dhana.parkinglots.service;

import com.dhana.parkinglots.entity.EntryExitPanel;
import com.dhana.parkinglots.entity.ParkingSpotType;

import java.util.Map;
import java.util.Set;

public interface EntryExitPanelService {
    Set<EntryExitPanel> getAll();
    EntryExitPanel getById(int id);
    EntryExitPanel create(Map<String,Object> panelInput);
    void delete(Map<String,Object> panelInput);
}
