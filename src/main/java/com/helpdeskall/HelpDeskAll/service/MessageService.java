package com.helpdeskall.HelpDeskAll.service;

import com.helpdeskall.HelpDeskAll.domain.Message;
import com.helpdeskall.HelpDeskAll.domain.Ticket;
import com.helpdeskall.HelpDeskAll.domain.User;
import com.helpdeskall.HelpDeskAll.dto.MessageCreateDTO;
import com.helpdeskall.HelpDeskAll.dto.MessageResponseDTO;
import com.helpdeskall.HelpDeskAll.exception.TicketNotFoundException;
import com.helpdeskall.HelpDeskAll.exception.UserNotFoundException;
import com.helpdeskall.HelpDeskAll.repository.MessageRepository;
import com.helpdeskall.HelpDeskAll.repository.TicketRepository;
import com.helpdeskall.HelpDeskAll.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public MessageResponseDTO create(MessageCreateDTO dto) {
        Ticket ticket = ticketRepository.findById(dto.ticketId())
                .orElseThrow(() -> new TicketNotFoundException(dto.ticketId()));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(dto.userId()));

        Message message = Message.builder()
                .ticket(ticket)
                .user(user)
                .content(dto.content())
                .build();

        Message saved = messageRepository.save(message);

        return MessageResponseDTO.fromEntity(saved);
    }

    public List<MessageResponseDTO> findByTicket(Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new TicketNotFoundException(ticketId);
        }

        return messageRepository.findByTicketIdOrderByCreatedAtAsc(ticketId)
                .stream()
                .map(MessageResponseDTO::fromEntity)
                .toList();
    }
}