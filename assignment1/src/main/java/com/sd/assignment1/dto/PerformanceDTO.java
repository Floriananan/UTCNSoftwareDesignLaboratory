package com.sd.assignment1.dto;

import com.sd.assignment1.model.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
public class PerformanceDTO {
    private long id;
    private String performer;
    private String genre;
    private String title;
    private Timestamp timestamp;
    private int ticketStock;
    List<TicketDTO> ticketDTOList;
}
