package com.example.courier_tracking.service;

import com.example.courier_tracking.model.Courier;
import com.example.courier_tracking.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourierService {

    @Autowired
    private CourierRepository courierRepository;

    // Tüm kuryeleri getir
    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    // ID ile tek bir kurye getir
    public Optional<Courier> getCourierById(Long id) {
        return courierRepository.findById(id);
    }

    // Yeni kurye oluştur
    public Courier createCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    // Mevcut kuryeyi güncelle
    public Courier updateCourier(Long id, Courier courierDetails) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Courier not found"));

        // Kurye bilgilerini güncelle
        courier.setName(courierDetails.getName());
        courier.setCreatedAt(courierDetails.getCreatedAt());

        return courierRepository.save(courier);
    }

    // Kurye sil
    public void deleteCourier(Long id) {
        courierRepository.deleteById(id);
    }
}
