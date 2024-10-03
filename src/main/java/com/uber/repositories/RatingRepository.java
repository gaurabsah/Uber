package com.uber.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uber.model.Driver;
import com.uber.model.Rating;
import com.uber.model.Ride;
import com.uber.model.Rider;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByRider(Rider rider);

	List<Rating> findByDriver(Driver driver);

	Optional<Rating> findByRide(Ride ride);
}
