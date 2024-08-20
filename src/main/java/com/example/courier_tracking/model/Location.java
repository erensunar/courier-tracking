package com.example.courier_tracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.example.courier_tracking.validation.ValidLatitude;
import com.example.courier_tracking.validation.ValidLongitude;

import java.time.LocalDateTime;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = false)
    @NotNull(message = "Courier cannot be null")
    private Courier courier;

    @ValidLatitude
    @NotNull(message = "Latitude cannot be null")
    @Column(nullable = false)
    private Double latitude;

    @ValidLongitude
    @NotNull(message = "Longitude cannot be null")
    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

    // Getter ve Setter metotları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
