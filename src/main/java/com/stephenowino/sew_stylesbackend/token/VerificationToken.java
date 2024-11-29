package com.stephenowino.sew_stylesbackend.token;

import com.stephenowino.sew_stylesbackend.Model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Sampson Alfred
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String token;

        private Date expirationTime;

        private static final int EXPIRATION_TIME = 15; // Expiration time in minutes

        @OneToOne
        @JoinColumn(name = "user_id")
        private User user;

        // Constructor to create a token with a user
        public VerificationToken(String token, User user) {
                this.token = token;
                this.user = user;
                this.expirationTime = this.getTokenExpirationTime();
        }

        // Constructor for token creation without user
        public VerificationToken(String token) {
                this.token = token;
                this.expirationTime = this.getTokenExpirationTime();
        }

        // Method to calculate expiration time
        public Date getTokenExpirationTime() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(new Date().getTime());
                calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
                return new Date(calendar.getTime().getTime());
        }

        // Static method to generate a token
        public static String generateToken() {
                return UUID.randomUUID().toString();
        }
}
