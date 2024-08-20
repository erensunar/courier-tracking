package com.example.courier_tracking.repository;

import com.example.courier_tracking.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    // Courier ID'sine göre Location'ları bul
    List<Location> findByCourierId(Long courierId);
}
