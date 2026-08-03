package com.helpdeskall.HelpDeskAll.dto;

import com.helpdeskall.HelpDeskAll.domain.Message;

import java.time.LocalDateTime;

public record MessageResponseDTO(
        Long id,
        Long ticketId,
        Long userId,
        String userName,
        String content,
        LocalDateTime createdAt
) {
    public static MessageResponseDTO fromEntity(Message message) {

        return new MessageResponseDTO(
                message.getId(),
                message.getTicket().getId(),
                message.getUser().getId(),
                message.getUser().getName(),
                message.getContent(),
                message.getCreatedAt()
        );
    }
}
