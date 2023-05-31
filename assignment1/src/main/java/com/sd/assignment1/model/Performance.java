package com.sd.assignment1.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "performances")
@Builder
@Getter
@Setter
@ToString

public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "performer")
    private String performer;
    @Column(name = "genre")
    private String genre;
    @Column(name = "title")
    private String title;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "ticketStock")
    private int ticketStock;

    @OneToMany (mappedBy = "performance")
    List<Ticket> ticketList;


}
