package com.sd.assignment1.service;

import com.sd.assignment1.dal.PerformanceRepo;
import com.sd.assignment1.dal.TicketRepo;
import com.sd.assignment1.dto.TicketDTO;
import com.sd.assignment1.mappers.TicketMapper;
import com.sd.assignment1.model.Performance;
import com.sd.assignment1.model.Ticket;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@Service
public class TicketService {
    TicketRepo ticketRepo;
    //@Autowired
    TicketMapper ticketMapper;
    PerformanceRepo performanceRepo;

    public TicketDTO createTicket(TicketDTO ticketDTO){
        Ticket ticket = ticketMapper.ticketDTO_TO_ticket(ticketDTO);
        Ticket createdTicket = ticketRepo.save(ticket);
        return ticketMapper.ticket_TO_ticketDTO(createdTicket);
    }
    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO){
        Ticket ticketHelper = ticketRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Performance not found"));
        Ticket ticketHelper2 = ticketMapper.ticketDTO_TO_ticket(ticketDTO);
        ticketHelper2.setId(ticketHelper.getId());


        Ticket updatedTicket = ticketRepo.save(ticketHelper2);
        return ticketMapper.ticket_TO_ticketDTO(updatedTicket);
    }

    public void deleteTicket(Long id){
        ticketRepo.deleteById(id);
    }

    public TicketDTO getTicketById(Long id){
        Ticket ticket = ticketRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Ticket not found"));
        return ticketMapper.ticket_TO_ticketDTO(ticket);
    }
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepo.findAll();

        return tickets.stream()
                .map(ticketMapper::ticket_TO_ticketDTO)
                .collect(Collectors.toList());
    }

    public List<TicketDTO> getTicketsForPerformance(Long idp){
        List<Ticket> tickets = ticketRepo.findByPerformanceId(idp);
        return tickets.stream()
                .map(ticketMapper::ticket_TO_ticketDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO addPerformanceToTicket(Long id, Long idp) {
        Ticket ticket = ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        Performance performance = performanceRepo.findById(idp).orElseThrow(() -> new EntityNotFoundException("Performance not found"));

        if (performance.getTicketStock() <= 0){
            throw new NoSuchElementException("Seats Unavailable");
        }
        ticket.setPerformance(performance);
        performance.setTicketStock(performance.getTicketStock() - 1);

        Ticket helper = ticketRepo.save(ticket);
        return ticketMapper.ticket_TO_ticketDTO(helper);

    }


}
