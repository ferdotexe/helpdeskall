package com.helpdeskall.HelpDeskAll.dto;

import com.helpdeskall.HelpDeskAll.domain.Ticket;
import com.helpdeskall.HelpDeskAll.domain.enums.TicketStatus;

public record TicketResponseDTO(Long id,
                                String title,
                                String description,
                                TicketStatus status,
                                Long createdBy,
                                String createdByName) {
    public static TicketResponseDTO fromEntity(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreatedBy().getId(),
                ticket.getCreatedBy().getName()
        );
    }
}
