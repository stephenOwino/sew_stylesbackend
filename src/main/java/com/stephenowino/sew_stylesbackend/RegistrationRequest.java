package com.stephenowino.sew_stylesbackend;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegistrationRequest(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,

        @NotBlank(message = "Role is required")
        String role,  // Ensure this role value is one of the predefined roles (e.g., USER, ADMIN, TAILOR)

        byte[] image, // Optional, no validation required

        Boolean isActive // Nullable Boolean
) {
        // You don't need a constructor here
}

