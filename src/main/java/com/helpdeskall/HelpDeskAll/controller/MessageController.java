package com.helpdeskall.HelpDeskAll.controller;

import com.helpdeskall.HelpDeskAll.dto.MessageCreateDTO;
import com.helpdeskall.HelpDeskAll.dto.MessageResponseDTO;
import com.helpdeskall.HelpDeskAll.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/api/messages")
    public ResponseEntity<MessageResponseDTO> create(@Valid @RequestBody MessageCreateDTO dto) {
        MessageResponseDTO response = messageService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/tickets/{ticketId}/messages")
    public ResponseEntity<List<MessageResponseDTO>> findByTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(messageService.findByTicket(ticketId));
    }
}