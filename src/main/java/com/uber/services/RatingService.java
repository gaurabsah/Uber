package com.uber.services;

import com.uber.dto.DriverDto;
import com.uber.dto.RiderDto;
import com.uber.model.Ride;

public interface RatingService {

    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
