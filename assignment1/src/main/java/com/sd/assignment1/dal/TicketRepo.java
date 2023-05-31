package com.sd.assignment1.dal;

import com.sd.assignment1.model.Ticket;
import com.sd.assignment1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findByPerformanceId(Long idp);
}
