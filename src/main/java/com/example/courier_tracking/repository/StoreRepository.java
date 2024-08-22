package com.example.courier_tracking.repository;

import com.example.courier_tracking.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByNameAndLatAndLng(String name, double lat, double lng);
}
