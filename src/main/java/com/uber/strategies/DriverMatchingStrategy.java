package com.uber.strategies;

import java.util.List;

import com.uber.model.Driver;
import com.uber.model.RideRequest;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
