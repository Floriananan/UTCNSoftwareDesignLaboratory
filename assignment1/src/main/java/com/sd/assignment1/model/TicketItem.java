//package com.sd.assignment1.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "ticketItems")
//@Builder
//@Getter
//@Setter
//@ToString
//
//public class TicketItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @ManyToOne
//    Ticket ticket;
//
//    @OneToOne
//    private Performance performance;
//
//
//}
