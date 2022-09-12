package com.dhana.parkinglots.service;

import com.dhana.parkinglots.entity.ElectricBill;

import java.util.Map;

public interface ElectricBillService {

    ElectricBill powerOn(int ticketId);

    ElectricBill getById(int id);

    ElectricBill powerOff(Map<String,String>  powerOffInput);

}
