package com.example.courier_tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.courier_tracking.model")
public class CourierTrackingApplication {
	public static void main(String[] args) {
		SpringApplication.run(CourierTrackingApplication.class, args);
	}
}
