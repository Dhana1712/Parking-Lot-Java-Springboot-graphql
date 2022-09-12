package com.dhana.parkinglots.entity;

import com.dhana.parkinglots.Enum.ElectricStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "electric_bill")
public class ElectricBill{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float energyConsumptionCost;

    @Enumerated(EnumType.STRING)
    private ElectricStatus status;

}