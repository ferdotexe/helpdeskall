package com.helpdeskall.HelpDeskAll.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket não encontrado com id: " + id);
    }

}
