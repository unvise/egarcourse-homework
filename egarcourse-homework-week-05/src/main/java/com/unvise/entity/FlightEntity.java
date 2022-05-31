package com.unvise.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "flight")
@Data
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_seq")
    @SequenceGenerator(name = "flight_id_seq", sequenceName = "flight_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "from")
    private String from;

    @Column(name = "to")
    private String to;

    @Column(name = "departure_time")
    private LocalDate departureTime;

    @Column(name = "arrival_time")
    private LocalDate arrivalTime;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("id")
    private AirplaneEntity airplane;

}
