package com.stephenowino.sew_stylesbackend;

import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.repository.UserRepository;
import com.stephenowino.sew_stylesbackend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

        @InjectMocks
        private UserService userService;

        @Mock
        private UserRepository userRepository;

        private User user;

        @BeforeEach
        void setUp() {
                MockitoAnnotations.openMocks(this); // Initialize mocks
                user = User.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("john.doe@example.com")
                        .password("password123")
                        .isActive(true) // Adjust based on your User class
                        .build();
        }

        @Test
        void testRegisterUser() {
                when(userRepository.save(any(User.class))).thenReturn(user);

                User registeredUser = userService.registerUser(user);

                assertNotNull(registeredUser);
                assertEquals("John", registeredUser.getFirstName());
                verify(userRepository, times(1)).save(any(User.class));
        }

        @Test
        void testGetUserById() {
                when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));

                User foundUser = userService.getUserById(1L);

                assertNotNull(foundUser);
                assertEquals("John", foundUser.getFirstName());
                verify(userRepository, times(1)).findById(1L);
        }
}


