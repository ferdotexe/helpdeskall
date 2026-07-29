package com.helpdeskall.HelpDeskAll.domain;

import com.helpdeskall.HelpDeskAll.domain.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TicketStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Message> messages = List.of();

}
