package com.helpdeskall.HelpDeskAll.service;

import com.helpdeskall.HelpDeskAll.domain.Ticket;
import com.helpdeskall.HelpDeskAll.domain.User;
import com.helpdeskall.HelpDeskAll.domain.enums.TicketStatus;
import com.helpdeskall.HelpDeskAll.dto.TicketCreateDTO;
import com.helpdeskall.HelpDeskAll.dto.TicketResponseDTO;
import com.helpdeskall.HelpDeskAll.exception.TicketNotFoundException;
import com.helpdeskall.HelpDeskAll.exception.UserNotFoundException;
import com.helpdeskall.HelpDeskAll.repository.TicketRepository;
import com.helpdeskall.HelpDeskAll.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketResponseDTO create(TicketCreateDTO dto) {
        User user = userRepository.findById(dto.createdBy())
                .orElseThrow(() -> new UserNotFoundException(dto.createdBy()));

        Ticket ticket = Ticket.builder()
                .title(dto.title())
                .description(dto.description())
                .status(TicketStatus.OPEN)
                .createdBy(user)
                .build();

        Ticket saved = ticketRepository.save(ticket);

        return TicketResponseDTO.fromEntity(saved);
    }

    public List<TicketResponseDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(TicketResponseDTO::fromEntity)
                .toList();
    }

    public TicketResponseDTO findById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        return TicketResponseDTO.fromEntity(ticket);
    }
}