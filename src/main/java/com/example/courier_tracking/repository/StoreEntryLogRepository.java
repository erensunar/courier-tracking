package com.example.courier_tracking.repository;

import com.example.courier_tracking.model.StoreEntryLog;
import com.example.courier_tracking.model.Courier;
import com.example.courier_tracking.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StoreEntryLogRepository extends JpaRepository<StoreEntryLog, Long> {
    Optional<StoreEntryLog> findTopByCourierAndStoreOrderByEntryTimeDesc(Courier courier, Store store);
}
