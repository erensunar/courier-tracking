package com.example.courier_tracking.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "couriers")
public class Courier {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Courier() {
        this.createdAt = LocalDateTime.now();
    }

    public Courier(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

}
