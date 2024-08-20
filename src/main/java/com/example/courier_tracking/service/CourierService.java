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

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Optional<Courier> getCourierById(Long id) {
        return courierRepository.findById(id);
    }

    public Courier createCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    public Courier updateCourier(Long id, Courier courierDetails) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Courier not found with id: " + id));

        courier.setName(courierDetails.getName());
        // Eğer courierDetails'dan bir tarih alacaksanız, bu şekilde kullanabilirsiniz
        courier.setCreatedAt(courierDetails.getCreatedAt()); // Örnek olarak createdAt set edilmesi

        return courierRepository.save(courier);
    }

    public void deleteCourier(Long id) {
        courierRepository.deleteById(id);
    }
}
