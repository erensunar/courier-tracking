package com.example.courier_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/map")
    public String showMap() {
        return "map.html"; // map.html dosyasını döner
    }
}
