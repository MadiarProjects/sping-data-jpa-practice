package org.example.springdatajpapractice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum Priority {
        LOW, MEDIUM, HIGH, SPECIAL
    }

    public enum Status {
        OPEN, IN_WORK, TESTING, CLOSED
    }
}
