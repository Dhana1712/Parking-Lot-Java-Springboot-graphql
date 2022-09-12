package com.dhana.parkinglots.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vehicleNumber;

    private String vehicleType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="entry_panel")
    private EntryExitPanel entryPanel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exit_panel")
    private EntryExitPanel exitPanel;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;

    private float parkingHours;

    private float parkingFare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "electric_bill_id" )
    private ElectricBill electricBill;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;



}
