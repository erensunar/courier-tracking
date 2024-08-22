package com.example.courier_tracking.service;

import com.example.courier_tracking.model.Courier;
import com.example.courier_tracking.model.Location;
import com.example.courier_tracking.repository.CourierRepository;
import com.example.courier_tracking.repository.LocationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CourierRepository courierRepository;

    public Location saveLocation(@Valid Location location) {
        // Öncelikle courier id ile veritabanında böyle bir kayıt var mı kontrol edelim.
        Long courierId = location.getCourier().getId();
        Optional<Courier> courier = courierRepository.findById(courierId);

        if (courier.isPresent()) {
            location.setCourier(courier.get());
            return locationRepository.save(location);
        } else {
            throw new IllegalArgumentException("Courier not found with id: " + courierId);
        }
    }

    public List<Location> getLocationsByCourierId(Long courierId) {
        return locationRepository.findByCourierId(courierId);
    }
    public List<Location> getLastLocationsForAllCouriers() {
        // Tüm kuryelerin son konumlarını getirir.
        List<Courier> couriers = courierRepository.findAll();
        return couriers.stream()
                .map(courier -> locationRepository.findTopByCourierOrderByTimestampDesc(courier))
                .collect(Collectors.toList());
    }
}
