package com.dhana.parkinglots.entity;

import com.dhana.parkinglots.Enum.ParkingSpotQuota;
import com.dhana.parkinglots.Enum.ParkingSpotStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parking_spot",uniqueConstraints = {@UniqueConstraint(columnNames ={"parking_spot_no","parking_spot_type","floor_id","parking_spot_quota"})})
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "parking_spot_no",nullable = false)
    private int parkingSpotNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_spot_type",nullable = false)
    private ParkingSpotType parkingSpotType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="floor_id" ,nullable = false)
    private ParkingFloor parkingFloor;

    @Enumerated(EnumType.STRING)
    @Column(name = "parking_spot_quota",nullable = false)
    private ParkingSpotQuota parkingSpotQuota;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingSpotStatus status;

    @OneToMany(mappedBy = "parkingSpot")
    private Set<Ticket> tickets;
}
