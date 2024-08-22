package com.example.courier_tracking.controller;

import com.example.courier_tracking.model.Store;
import com.example.courier_tracking.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {

    // StoreService'i enjekte edin
    @Autowired
    private StoreService storeService;

    // Tüm mağazaları getiren endpoint
    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getStores();
        System.out.println(stores);
        return ResponseEntity.ok(stores);
    }
}
