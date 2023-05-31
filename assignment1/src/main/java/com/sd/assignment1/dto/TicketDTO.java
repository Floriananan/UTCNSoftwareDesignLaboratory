package com.sd.assignment1.dto;

import com.sd.assignment1.model.Performance;
import com.sd.assignment1.model.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class TicketDTO {
    private long id;
    private Long performanceId;
}
