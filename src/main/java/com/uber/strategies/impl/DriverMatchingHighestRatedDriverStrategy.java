package com.uber.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uber.model.Driver;
import com.uber.model.RideRequest;
import com.uber.repositories.DriverRepository;
import com.uber.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional()
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

	private final DriverRepository driverRepository;

	@Override
	public List<Driver> findMatchingDriver(RideRequest rideRequest) {
		return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
	}
}
