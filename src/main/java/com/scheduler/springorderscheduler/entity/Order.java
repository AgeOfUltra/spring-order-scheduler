package com.scheduler.springorderscheduler.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerEmail;
    private String status; // PENDING, PROCESSING, COMPLETED
    private LocalDateTime createdAt;

    @PrePersist //automatically executes a method before an entity is persisted (saved) to the database for the first time.
    public void prePersist() {
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }
}
