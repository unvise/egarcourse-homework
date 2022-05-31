package com.unvise.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "airplane")
@Data
public class AirplaneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airplane_id_seq")
    @SequenceGenerator(name = "airplane_id_seq", sequenceName = "airplane_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "license_plate")
    private String licensePlate;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("id")
    private AirlineEntity airline;

}
