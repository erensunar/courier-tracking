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
    public Double getTotalTravelDistance(Long courierId) {
        List<Location> locations = locationRepository.findByCourierIdOrderByTimestampAsc(courierId);

        if (locations.size() < 2) {
            return 0.0; // Eğer bir veya daha az lokasyon varsa, mesafe sıfırdır.
        }

        double totalDistance = 0.0;
        Location previousLocation = locations.get(0);

        for (int i = 1; i < locations.size(); i++) {
            Location currentLocation = locations.get(i);
            totalDistance += calculateDistance(previousLocation, currentLocation);
            previousLocation = currentLocation;
        }

        return totalDistance;
    }

    private double calculateDistance(Location loc1, Location loc2) {
        final int R = 6371; // Radius of the earth in km

        double latDistance = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double lngDistance = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude()))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in km
    }

}
