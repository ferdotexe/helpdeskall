package com.helpdeskall.HelpDeskAll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageCreateDTO(
        @NotNull(message = "Ticket é obrigatório")
        Long ticketId,

        @NotNull(message = "Usuário é obrigatório")
        Long userId,

        @NotBlank(message = "Conteúdo é obrigatório")
        String content
) {
}
