package com.sd.assignment1.mappers;

import com.sd.assignment1.dto.TicketDTO;
import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.model.Performance;
import com.sd.assignment1.model.Ticket;
import com.sd.assignment1.model.User;

public class TicketMapper {
    PerformanceMapper performanceMapper;
    public TicketDTO ticket_TO_ticketDTO(Ticket ticket){
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setPerformanceId(ticket.getPerformance().getId());

        return ticketDTO;
    }
    public Ticket ticketDTO_TO_ticket(TicketDTO ticketDTO){
        Ticket ticket = new Ticket();
        //ticket.setId(ticketDTO.getId());

        //PerformanceDTO performanceDTO = new
        Performance performance = new Performance();
        performance.setId(ticketDTO.getPerformanceId());
        ticket.setPerformance(performance);

        return ticket;
    }
}
