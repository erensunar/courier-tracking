package com.example.courier_tracking.service;

import com.example.courier_tracking.model.Courier;
import com.example.courier_tracking.model.Store;
import com.example.courier_tracking.model.StoreEntryLog;
import com.example.courier_tracking.repository.StoreEntryLogRepository;
import com.example.courier_tracking.util.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourierTrackingService {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreEntryLogRepository storeEntryLogRepository;

    public void checkAndLogEntry(Courier courier, double lat, double lng) {
        System.out.println("Checking entry for courier: " + courier.getName());
        for (Store store : storeService.getStores()) {
            double distance = LocationUtils.calculateDistance(lat, lng, store.getLat(), store.getLng());
            if (distance <= 100) {
                System.out.println("Courier is within 100 meters of store: " + store.getName());
                Optional<StoreEntryLog> lastEntry = storeEntryLogRepository.findTopByCourierAndStoreOrderByEntryTimeDesc(courier, store);
                if (lastEntry.isEmpty() || Duration.between(lastEntry.get().getEntryTime(), LocalDateTime.now()).toMinutes() >= 1) {
                    StoreEntryLog entryLog = new StoreEntryLog();
                    entryLog.setCourier(courier);
                    entryLog.setStore(store);
                    entryLog.setEntryTime(LocalDateTime.now());
                    storeEntryLogRepository.save(entryLog);
                    System.out.println("Logged entry for courier: " + courier.getName() + " at store: " + store.getName());
                } else {
                    System.out.println("Courier reentry ignored: " + courier.getName() + " at store: " + store.getName());
                }
            } else {
                System.out.println("Courier is not within 100 meters of any store");
            }
        }
    }
}
