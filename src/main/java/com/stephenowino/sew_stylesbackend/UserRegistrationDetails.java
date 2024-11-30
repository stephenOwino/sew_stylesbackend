package com.stephenowino.sew_stylesbackend;

import com.stephenowino.sew_stylesbackend.Model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of UserDetails for Sew Styles project.
 * Represents the authenticated user details for security purposes.
 */
@Data
public class UserRegistrationDetails implements UserDetails {

        private String email; // Email used for login
        private String password; // Encrypted password
        private boolean enabled; // Account enabled/disabled status
        private List<GrantedAuthority> authorities; // Roles or permissions assigned to the user

        public UserRegistrationDetails(User user) {
                this.email = user.getEmail(); // Using email as the username for authentication
                this.password = user.getPassword(); // Encrypted password
                this.enabled = user.isEnabled(); // Account status
                // Convert the Role enum to a SimpleGrantedAuthority
                this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return email; // Email is used as the username
        }

        @Override
        public boolean isAccountNonExpired() {
                return true; // Account is always non-expired
        }

        @Override
        public boolean isAccountNonLocked() {
                return true; // Account is always non-locked by default
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true; // Credentials are always non-expired
        }

        @Override
        public boolean isEnabled() {
                return enabled; // Reflects the user's enabled status
        }
}
