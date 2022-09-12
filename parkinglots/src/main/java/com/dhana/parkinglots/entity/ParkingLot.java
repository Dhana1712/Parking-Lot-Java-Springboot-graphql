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
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    private String id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "parkingLot",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ParkingFloor> parkingFloors;

    @OneToMany(mappedBy = "parkingLot",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "parkingLot",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<EntryExitPanel> entryExitPanels;

}
