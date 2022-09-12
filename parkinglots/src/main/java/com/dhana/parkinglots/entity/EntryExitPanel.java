package com.dhana.parkinglots.entity;

import com.dhana.parkinglots.Enum.PanelType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entry_exit_panel",uniqueConstraints = {@UniqueConstraint(columnNames = {"panel_name","parking_lot_id","panel_type"})})
public class EntryExitPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "panel_name",nullable = false)
    private String panelName;

    @Column(name = "panel_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private PanelType panelType;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id",nullable = false)
    private ParkingLot parkingLot;
}
