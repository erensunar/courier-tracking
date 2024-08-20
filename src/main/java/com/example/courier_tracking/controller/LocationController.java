package com.example.courier_tracking.controller;

import com.example.courier_tracking.model.Location;
import com.example.courier_tracking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<Location> saveLocation(@RequestBody Location location) {
        try {
            Location savedLocation = locationService.saveLocation(location);
            return ResponseEntity.ok(savedLocation);
        } catch (IllegalArgumentException e) {
            // Hata varsa logla ve 404 döndür
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/courier/{courierId}")
    public ResponseEntity<List<Location>> getLocationsByCourierId(@PathVariable Long courierId) {
        List<Location> locations = locationService.getLocationsByCourierId(courierId);
        return ResponseEntity.ok(locations);
    }
}
