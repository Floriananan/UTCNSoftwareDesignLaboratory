package com.sd.assignment1.controller;

import com.sd.assignment1.dto.TicketDTO;
import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.model.Ticket;
import com.sd.assignment1.service.PerformanceService;
import com.sd.assignment1.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashier")
public class TicketCtrl {
    //@Autowired
    TicketService ticketService;
    //@Autowired
    PerformanceService performanceService;
    public TicketCtrl(TicketService ticketService, PerformanceService performanceService){
        this.ticketService = ticketService;
        this.performanceService = performanceService;

    }

    @PostMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> createTicket(@PathVariable Long id){
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(id);
        TicketDTO ticket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PostMapping("/tickets/{id}/p/{idp}")//first is ticket id, second is performance id
    public ResponseEntity<TicketDTO> createTicket(
            @PathVariable Long id,
            @PathVariable Long idp
            ){
        //find
        TicketDTO helper = ticketService.addPerformanceToTicket(id, idp);
        if (helper == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(helper);
    }

    @DeleteMapping("tickets/{id}")
    public ResponseEntity<TicketDTO> deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("tickets/performance/{id}")
    public ResponseEntity<List<TicketDTO>> getTicketsForPerformance(@PathVariable Long id){
        List<TicketDTO> ticketDTOList = ticketService.getTicketsForPerformance(id);
        return ResponseEntity.ok(ticketDTOList);
    }

}
