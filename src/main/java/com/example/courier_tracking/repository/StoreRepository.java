package com.example.courier_tracking.repository;

import com.example.courier_tracking.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // İhtiyaca göre ek sorgular eklenebilir

}
