package com.helpdeskall.HelpDeskAll.service;

import com.helpdeskall.HelpDeskAll.domain.User;
import com.helpdeskall.HelpDeskAll.dto.UserRegisterDTO;
import com.helpdeskall.HelpDeskAll.dto.UserResponseDTO;
import com.helpdeskall.HelpDeskAll.exception.EmailAlreadyExistsException;
import com.helpdeskall.HelpDeskAll.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRegisterDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException(dto.email());
        }

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(dto.role())
                .build();

        User saved = userRepository.save(user);

        return UserResponseDTO.fromEntity(saved);
    }
}