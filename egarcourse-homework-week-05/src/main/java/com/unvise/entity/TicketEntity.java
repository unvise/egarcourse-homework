package com.unvise.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ticket")
@Data
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq")
    @SequenceGenerator(name = "ticket_id_seq", sequenceName = "ticket_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fare")
    private BigDecimal fare;

    @Column(name = "currency")
    private String currency;

    @Column(name = "expired")
    private Boolean expired;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("id")
    private FlightEntity flight;

    @Embedded
    private Audit audit = new Audit();

}
