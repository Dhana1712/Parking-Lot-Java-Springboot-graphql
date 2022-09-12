package com.dhana.parkinglots.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "parking_floor",uniqueConstraints = {@UniqueConstraint(columnNames = {"floor_no","parking_lot_id"})})
public class ParkingFloor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "floor_no",nullable = false)
    private int floorNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_lot_id",nullable = false)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingFloor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ParkingSpot> parkingSpots;
}
