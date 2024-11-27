package com.stephenowino.sew_stylesbackend.Model;


import com.stephenowino.sew_stylesbackend.Role.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;
        private String lastName;

        @Column(unique = true, nullable = false)
        private String email;

        private String password;

        @Lob
        private byte[] image; // Profile picture, optional

        @Enumerated(EnumType.STRING)
        private Role role; // Differentiates between USER and TAILOR

        private boolean isActive; // For enabling/disabling accounts
}

