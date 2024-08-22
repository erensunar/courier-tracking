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
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    private List<Store> stores;

    @PostConstruct
    public void loadStores() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = new ClassPathResource("stores.json").getInputStream();
            List<Store> storesFromFile = mapper.readValue(inputStream, new TypeReference<List<Store>>() {});

            // Veritabanında mağaza olup olmadığını kontrol edin
            for (Store store : storesFromFile) {
                Optional<Store> existingStore = storeRepository.findByNameAndLatAndLng(store.getName(), store.getLat(), store.getLng());

                if (!existingStore.isPresent()) {
                    storeRepository.save(store);
                    System.out.println("Store saved: " + store.getName());
                } else {
                    System.out.println("Store already exists: " + store.getName());
                }
            }

        } catch (IOException e) {
            System.err.println("Error loading stores: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("General error in loadStores: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public List<Store> getStores() {
        return stores;
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }
}
