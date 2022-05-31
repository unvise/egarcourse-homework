package com.unvise.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passenger")
@Data
@NamedEntityGraph(
        name = "passenger-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "tickets", subgraph = "tickets-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(name = "tickets-subgraph", attributeNodes = {@NamedAttributeNode("flight")})
        }
)
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passenger_id_seq")
    @SequenceGenerator(name = "passenger_id_seq", sequenceName = "passenger_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "ticket_passenger",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id")
    )
    private List<TicketEntity> tickets;

}
