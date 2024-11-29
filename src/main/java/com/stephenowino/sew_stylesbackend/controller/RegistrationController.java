package com.stephenowino.sew_stylesbackend.controller;

import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.RegistrationRequest;
import com.stephenowino.sew_stylesbackend.event.listener.RegistrationCompleteEvent;
import com.stephenowino.sew_stylesbackend.services.UserService;
import com.stephenowino.sew_stylesbackend.token.TokenRepository;
import com.stephenowino.sew_stylesbackend.token.VerificationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

        private final UserService userService;
        private final ApplicationEventPublisher publisher;
        private final TokenRepository tokenRepository;

        /**
         * Handles user registration and triggers a registration complete event to send an email verification link.
         */
        @PostMapping
        public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest, HttpServletRequest request) {
                User user = userService.registerUser(registrationRequest);

                // Publish an event to send an email verification link
                publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Success! Please, check your email to complete your registration.");
        }

        /**
         * Verifies the email using the token sent in the verification link.
         */
        @GetMapping("/verifyEmail")
        public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
                VerificationToken theToken = tokenRepository.findByToken(token);

                if (theToken == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid verification token.");
                }

                if (theToken.getUser().isActive()) { // Using `isActive()` to check if the account is already verified
                        return ResponseEntity.ok("This account has already been verified. Please, login.");
                }

                String verificationResult = userService.validateToken(token);

                if (verificationResult.equalsIgnoreCase("valid")) {
                        return ResponseEntity.ok("Email verified successfully. Now you can login to your account.");
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification token.");
        }

        /**
         * Constructs the application URL dynamically for verification links.
         */
        private String applicationUrl(HttpServletRequest request) {
                return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        }
}


