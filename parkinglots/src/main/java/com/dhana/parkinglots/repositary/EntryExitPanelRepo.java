package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.Enum.PanelType;
import com.dhana.parkinglots.entity.EntryExitPanel;
import com.dhana.parkinglots.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EntryExitPanelRepo extends JpaRepository<EntryExitPanel,Integer> {


    EntryExitPanel findByPanelNameAndPanelTypeAndParkingLot(String panelName, PanelType panelType, ParkingLot parkingLot);

    Set<EntryExitPanel> findAllBy();
}
