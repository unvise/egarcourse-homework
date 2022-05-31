package com.unvise.entity;

import lombok.Data;

@Data
public class AirplaneEntity {
    private Integer id;
    private String model;
    private String licensePlate;
    private AirlineEntity airlineEntity;
}
