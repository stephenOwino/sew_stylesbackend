package com.stephenowino.sew_stylesbackend.services;

import com.stephenowino.sew_stylesbackend.Model.Tailor;
import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.Role.Role;
import com.stephenowino.sew_stylesbackend.repository.TailorRepository;
import com.stephenowino.sew_stylesbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TailorService {

        private final TailorRepository tailorRepository;
        private final UserRepository userRepository; // If you need to fetch the user data

        public TailorService(TailorRepository tailorRepository, UserRepository userRepository) {
                this.tailorRepository = tailorRepository;
                this.userRepository = userRepository;
        }

        // Create a tailor profile
        @Transactional
        public Tailor createTailorProfile(Long userId, Tailor tailorDetails) {
                // Fetch the user and check if the user is already a tailor
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

                // Ensure the user is not already a tailor
                if (user.getRole() == Role.TAILOR) {
                        throw new IllegalStateException("User is already a tailor");
                }

                // Set user to tailor profile
                tailorDetails.setUser(user); // Associate the user with the tailor

                // Save the tailor profile
                return tailorRepository.save(tailorDetails);
        }

        // Get a tailor's profile
        public Tailor getTailorProfile(Long id) {
                return tailorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Tailor not found"));
        }
}


