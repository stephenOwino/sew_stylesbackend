package com.stephenowino.sew_stylesbackend.services;


import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        private final UserRepository userRepository;
        private final BCryptPasswordEncoder passwordEncoder;

        @Autowired
        public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
        }

        public void registerUser(User user) {
                // Encrypt the password before saving the user
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
        }

        public User getUserById(Long id) {
                return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        }
}

