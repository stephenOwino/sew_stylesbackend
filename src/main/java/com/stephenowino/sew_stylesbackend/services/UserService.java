package com.stephenowino.sew_stylesbackend.services;

import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.RegistrationRequest;
import com.stephenowino.sew_stylesbackend.Role.Role;
import com.stephenowino.sew_stylesbackend.repository.UserRepository;
import com.stephenowino.sew_stylesbackend.token.TokenRepository;
import com.stephenowino.sew_stylesbackend.token.VerificationToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
        private final UserRepository userRepository;
        private final TokenRepository tokenRepository;
        private final BCryptPasswordEncoder passwordEncoder;

        @Autowired
        public UserService(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.tokenRepository = tokenRepository;
                this.passwordEncoder = passwordEncoder;
        }

        public List<User> getUsers() {
                return userRepository.findAll();
        }

        public User registerUser(@Valid RegistrationRequest request) {
                Optional<User> user = this.userRepository.findByEmail(request.email());
                if (user.isPresent()) {
                        throw new RuntimeException("User with email " + request.email() + " already exists");
                }

                var newUser = User.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .email(request.email())
                        .password(passwordEncoder.encode(request.password()))
                        .role(Role.valueOf(request.role()))
                        .image(request.image())
                        .isActive(request.isActive())
                        .build();

                return userRepository.save(newUser);
        }

        // Find by ID returning Optional
        public Optional<User> findById(Long id) {
                return userRepository.findById(id);
        }

        // Get by ID throwing exception if not found
        public User getUserById(Long id) {
                return userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
        }

        public Optional<User> findByEmail(String email) {
                return userRepository.findByEmail(email);
        }

        public void saveUserVerificationToken(User theUser, String token) {
                var verificationToken = new VerificationToken(token, theUser);
                tokenRepository.save(verificationToken);
        }

        public String validateToken(String theToken) {
                VerificationToken token = tokenRepository.findByToken(theToken);
                if (token == null) {
                        return "Invalid verification token";
                }

                User user = token.getUser();
                Calendar calendar = Calendar.getInstance();
                if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
                        tokenRepository.delete(token);
                        return "Token already expired";
                }

                user.setActive(true);
                userRepository.save(user);
                return "Valid";
        }
}




