package com.uber.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uber.dto.SignupDto;
import com.uber.dto.UserDto;
import com.uber.model.User;
import com.uber.model.enums.Role;
import com.uber.repositories.UserRepository;
import com.uber.security.JWTService;
import com.uber.services.impl.AuthServiceImpl;
import com.uber.services.impl.DriverServiceImpl;
import com.uber.services.impl.RiderServiceImpl;
import com.uber.services.impl.WalletServiceImpl;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JWTService jwtService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RiderServiceImpl riderService;

	@Mock
	private WalletServiceImpl walletService;

	@Mock
	private DriverServiceImpl driverService;

	@Spy
	private ModelMapper modelMapper;

	@Spy
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private AuthServiceImpl authService;

	private User user;

	@BeforeEach
	void setUp() {
		user = new User();
		user.setId(1L);
		user.setEmail("test@example.com");
		user.setPassword("password");
		user.setRoles(Set.of(Role.RIDER));
	}

	@Test
	void testLogin_whenSuccess() {
//        arrange
		Authentication authentication = mock(Authentication.class);
		when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
		when(authentication.getPrincipal()).thenReturn(user);
		when(jwtService.generateAccessToken(any(User.class))).thenReturn("accessToken");
		when(jwtService.generateRefreshToken(any(User.class))).thenReturn("refreshToken");

//        act
		String[] tokens = authService.login(user.getEmail(), user.getPassword());

//        assert
		assertThat(tokens).hasSize(2);
		assertThat(tokens[0]).isEqualTo("accessToken");
		assertThat(tokens[1]).isEqualTo("refreshToken");
	}

	@Test
    void testSignup_whenSuccess() {
        // Arrange
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        SignupDto signupDto = new SignupDto();
        signupDto.setEmail("test@example.com");
        signupDto.setPassword("password");
        UserDto userDto = authService.signup(signupDto);

        // Assert
        assertThat(userDto).isNotNull();
        assertThat(userDto.getEmail()).isEqualTo(signupDto.getEmail());
        verify(riderService).createNewRider(any(User.class));
        verify(walletService).createNewWallet(any(User.class));
    }
}