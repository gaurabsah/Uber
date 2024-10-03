package com.uber.services;

import com.uber.dto.DriverDto;
import com.uber.dto.SignupDto;
import com.uber.dto.UserDto;

public interface AuthService {

	String[] login(String email, String password);

	UserDto signup(SignupDto signupDto);

	DriverDto onboardNewDriver(Long userId, String vehicleId);

	String refreshToken(String refreshToken);
}
