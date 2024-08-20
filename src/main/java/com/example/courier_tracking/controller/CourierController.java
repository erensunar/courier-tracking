package com.example.courier_tracking.controller;

import com.example.courier_tracking.model.Courier;
import com.example.courier_tracking.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/couriers")
public class CourierController {

    @Autowired
    private CourierService courierService;

    // Tüm kuryeleri getir
    @GetMapping
    public List<Courier> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    // ID ile kurye getir
    @GetMapping("/{id}")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long id) {
        Optional<Courier> courier = courierService.getCourierById(id);
        return courier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Yeni kurye oluştur
    @PostMapping
    public Courier createCourier(@RequestBody Courier courier) {
        return courierService.createCourier(courier);
    }

    // Mevcut kuryeyi güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Courier> updateCourier(@PathVariable Long id, @RequestBody Courier courierDetails) {
        return ResponseEntity.ok(courierService.updateCourier(id, courierDetails));
    }


    // Kuryeyi sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long id) {
        courierService.deleteCourier(id);
        return ResponseEntity.noContent().build();
    }
}
