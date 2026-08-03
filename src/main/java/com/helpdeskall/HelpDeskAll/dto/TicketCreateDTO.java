package com.helpdeskall.HelpDeskAll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketCreateDTO(@NotBlank(message = "Título é obrigatório")
                               String title,

                              @NotBlank(message = "Descrição é obrigatória")
                               String description,

                              @NotNull(message = "Usuário criador é obrigatório")
                               Long createdBy) {
}
