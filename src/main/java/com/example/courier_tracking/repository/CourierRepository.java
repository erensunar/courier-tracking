package com.example.courier_tracking.repository;

import com.example.courier_tracking.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    // Ek sorgular tanımlayabilirsiniz
}
