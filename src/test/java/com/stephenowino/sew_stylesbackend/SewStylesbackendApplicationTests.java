package com.stephenowino.sew_stylesbackend;

import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.repository.UserRepository;
import com.stephenowino.sew_stylesbackend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SewStylesbackendApplicationTests {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	private User user;
	private RegistrationRequest registrationRequest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Create a User object
		user = User.builder()
			.firstName("Jane")
			.lastName("Doe")
			.email("jane.doe@example.com")
			.password("password123")
			.isActive(true)
			.build();

		// Create a RegistrationRequest object
		registrationRequest = new RegistrationRequest(
			"Jane",
			"Doe",
			"jane.doe@example.com",
			"password123",
			"USER", // Role
			null, // No image
			true // isActive
		);
	}

	@Test
	void contextLoads() {
		// This is to check if the Spring context loads successfully
	}

	@Test
	void testRegisterUser() {
		// Arrange: Mock the save method to return the user object
		when(userRepository.save(any(User.class))).thenReturn(user);

		// Act: Call the registerUser method with RegistrationRequest
		User registeredUser = userService.registerUser(registrationRequest);

		// Assert: Check if the user is returned as expected
		assertNotNull(registeredUser);
		assertEquals("Jane", registeredUser.getFirstName());
		assertEquals("jane.doe@example.com", registeredUser.getEmail());

		// Verify that the save method was called exactly once
		verify(userRepository, times(1)).save(any(User.class));
	}
}
