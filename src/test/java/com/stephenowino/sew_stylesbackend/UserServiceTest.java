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
                MockitoAnnotations.openMocks(this);
                user = User.builder().firstName("John").lastName("Doe").email("john.doe@example.com").password("password123").isTailor(false).build();
        }

        @Test
        void testRegisterUser() {
                when(userRepository.save(any(User.class))).thenReturn(user);

                userService.registerUser(user);

                verify(userRepository, times(1)).save(user);
        }

        @Test
        void testGetUserById() {
                when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));

                User foundUser = userService.getUserById(1L);

                assertNotNull(foundUser);
                assertEquals("John", foundUser.getFirstName());
        }
}

