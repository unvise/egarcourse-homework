package com.unvise.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "airline")
@Data
public class AirlineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airline_id_seq")
    @SequenceGenerator(name = "airline_id_seq", sequenceName = "airline_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

}
