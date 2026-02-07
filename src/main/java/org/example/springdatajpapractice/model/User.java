package org.example.springdatajpapractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.ORDINAL)
    private Role role;
    private String login;
    private String password;
    @CreationTimestamp
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
}
