package com.stephenowino.sew_stylesbackend.controller;

import com.stephenowino.sew_stylesbackend.Model.Tailor;
import com.stephenowino.sew_stylesbackend.services.TailorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tailors")
public class TailorController {

        private final TailorService tailorService;

        public TailorController(TailorService tailorService) {
                this.tailorService = tailorService;
        }

        @PostMapping
        public ResponseEntity<String> createTailorProfile(@RequestBody Tailor tailor) {
                tailorService.createTailorProfile(tailor);
                return ResponseEntity.ok("Tailor profile created successfully!");
        }

        @GetMapping("/{id}")
        public ResponseEntity<Tailor> getTailorProfile(@PathVariable Long id) {
                return ResponseEntity.ok(tailorService.getTailorProfile(id));
        }
}

