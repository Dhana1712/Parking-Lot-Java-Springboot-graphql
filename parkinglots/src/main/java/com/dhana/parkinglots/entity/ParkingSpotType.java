package com.dhana.parkinglots.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parking_spot_type")
public class ParkingSpotType {

    @Id
    @Column(name = "parking_spot_type")
    private String parkingSpotType;

    @Column(nullable = false)
    private int farePerHour;

}
