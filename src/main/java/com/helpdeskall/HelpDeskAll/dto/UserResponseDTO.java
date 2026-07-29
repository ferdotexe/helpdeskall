package com.helpdeskall.HelpDeskAll.dto;

import com.helpdeskall.HelpDeskAll.domain.User;
import com.helpdeskall.HelpDeskAll.domain.enums.Role;

public record UserResponseDTO(
    Long id,
    String name,
    String email,
    Role role
) {
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
