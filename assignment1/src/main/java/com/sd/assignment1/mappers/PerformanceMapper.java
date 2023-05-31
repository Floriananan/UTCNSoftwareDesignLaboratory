package com.sd.assignment1.mappers;

import com.sd.assignment1.dto.PerformanceDTO;
import com.sd.assignment1.dto.TicketDTO;
import com.sd.assignment1.dto.UserDTO;
import com.sd.assignment1.model.Performance;
import com.sd.assignment1.model.Ticket;
import com.sd.assignment1.model.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class PerformanceMapper {
    TicketMapper ticketMapper;
    public PerformanceDTO performance_TO_performanceDTO(Performance performance){
        PerformanceDTO performanceDTO = new PerformanceDTO();
        performanceDTO.setId(performance.getId());
        performanceDTO.setPerformer(performance.getPerformer());
        performanceDTO.setGenre(performance.getGenre());
        performanceDTO.setTitle(performance.getTitle());
        performanceDTO.setTimestamp(performance.getTimestamp());
        performanceDTO.setTicketStock(performance.getTicketStock());
        performanceDTO.setTicketDTOList(helpersD(performance.getTicketList()));

        return performanceDTO;
    }
    public Performance performanceDTO_TO_performance(PerformanceDTO performanceDTO){
        Performance performance = new Performance();
        //performance.setId(performanceDTO.getId());
        performance.setPerformer(performanceDTO.getPerformer());
        performance.setGenre(performanceDTO.getGenre());
        performance.setTitle(performanceDTO.getTitle());
        performance.setTimestamp(performanceDTO.getTimestamp());
        performance.setTicketStock(performanceDTO.getTicketStock());
        performance.setTicketList(helpers(performanceDTO.getTicketDTOList()));
        return performance;
    }

    public List<TicketDTO> helpersD (List<Ticket> tickets){
        return tickets.stream()
                .map(ticketMapper::ticket_TO_ticketDTO)
                .collect(Collectors.toList());
    }
    public List<Ticket> helpers (List<TicketDTO> ticketDTOs){
        return ticketDTOs.stream()
                .map(ticketMapper::ticketDTO_TO_ticket)
                .collect(Collectors.toList());
    }
////    public TicketDTO helper(Ticket ticket){
//        TicketDTO tDTO = new TicketDTO();
//        tDTO.setId(ticket.getId());
//        tDTO.setId(ticket.getId());
//
//    }
}


//
//    private long id;
//    private String performer;
//    private String genre;
//    private String title;
//    private Timestamp timestamp;
//    private int ticketStock;
//    List<TicketDTO> ticketDTOList;