package com.example.courier_tracking.util;

public class LocationUtils {

    public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        System.out.println("Calculating distance between points: (" + lat1 + ", " + lng1 + ") and (" + lat2 + ", " + lng2 + ")");
        final int R = 6371000; // Earth radius in meters

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lng2 - lng1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        System.out.println("Calculated distance: " + distance + " meters");
        return distance;
    }
}
