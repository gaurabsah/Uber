package com.uber.strategies.impl;
import org.springframework.stereotype.Service;

import com.uber.model.RideRequest;
import com.uber.services.DistanceService;
import com.uber.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }
}
