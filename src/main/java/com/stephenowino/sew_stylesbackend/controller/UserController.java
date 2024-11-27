package com.stephenowino.sew_stylesbackend.controller;

import com.stephenowino.sew_stylesbackend.Model.User;
import com.stephenowino.sew_stylesbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
                this.userService = userService;
        }

        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@RequestBody User user) {
                userService.registerUser(user);
                return ResponseEntity.ok("User registered successfully!");
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
                return ResponseEntity.ok(userService.getUserById(id));
        }
}

