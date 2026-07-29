package com.helpdeskall.HelpDeskAll.repository;


import com.helpdeskall.HelpDeskAll.domain.Ticket;
import com.helpdeskall.HelpDeskAll.domain.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCreatedById(Long userId);
    List<Ticket> findByStatus(TicketStatus status);
}