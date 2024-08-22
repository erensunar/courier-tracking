package com.example.courier_tracking.service;

import com.example.courier_tracking.model.Store;
import com.example.courier_tracking.repository.StoreRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @PostConstruct
    public void loadStores() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = new ClassPathResource("stores.json").getInputStream();
            List<Store> stores = mapper.readValue(inputStream, new TypeReference<List<Store>>() {});

            // Veritabanına store nesnelerini kaydet
            storeRepository.saveAll(stores);

            System.out.println("Stores loaded and saved: " + stores.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }
}
