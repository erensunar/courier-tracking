package com.example.courier_tracking.controller;

import com.example.courier_tracking.model.Location;
import com.example.courier_tracking.service.CourierTrackingService;
import com.example.courier_tracking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private CourierTrackingService courierTrackingService;

    @PostMapping
    public ResponseEntity<Location> saveLocation(@Valid @RequestBody Location location) {
        try {
            Location savedLocation = locationService.saveLocation(location);
            courierTrackingService.checkAndLogEntry(location.getCourier(), location.getLatitude(), location.getLongitude());
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

    // Validation hatalarını yakalamak için eklenen exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping("/last-locations")
    public ResponseEntity<List<Location>> getLastLocations() {
        List<Location> lastLocations = locationService.getLastLocationsForAllCouriers();
        System.out.println(lastLocations);
        return ResponseEntity.ok(lastLocations);
    }
}
