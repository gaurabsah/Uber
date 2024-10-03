package com.uber.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uber.model.Driver;
import com.uber.model.RideRequest;
import com.uber.repositories.DriverRepository;
import com.uber.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

	private final DriverRepository driverRepository;

	@Override
	public List<Driver> findMatchingDriver(RideRequest rideRequest) {
		return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
	}
}
