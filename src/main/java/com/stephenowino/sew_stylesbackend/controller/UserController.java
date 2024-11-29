package com.stephenowino.sew_stylesbackend.controller;


import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.RegistrationRequest;
import com.stephenowino.sew_stylesbackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;

        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
                userService.registerUser(registrationRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
                return userService.findById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        }
}


