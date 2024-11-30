package com.stephenowino.sew_stylesbackend;


import com.stephenowino.sew_stylesbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
        @RequiredArgsConstructor
        public class UserRegistrationDetailsService implements UserDetailsService {
                private final UserRepository userRepository;

                @Override
                public UserRegistrationDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                        return userRepository.findByEmail(email)
                                .map(UserRegistrationDetails::new)
                                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
                }
}
