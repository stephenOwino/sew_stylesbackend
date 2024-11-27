package com.stephenowino.sew_stylesbackend.controller;

import com.stephenowino.sew_stylesbackend.Model.Gallery;
import com.stephenowino.sew_stylesbackend.services.GalleryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

        private final GalleryService galleryService;

        public GalleryController(GalleryService galleryService) {
                this.galleryService = galleryService;
        }

        @PostMapping
        public ResponseEntity<String> createGallery(@RequestBody Gallery gallery) {
                galleryService.createGallery(gallery);
                return ResponseEntity.ok("Gallery created successfully!");
        }

        @GetMapping("/tailor/{tailorId}")
        public ResponseEntity<List<Gallery>> getGalleriesByTailor(@PathVariable Long tailorId) {
                return ResponseEntity.ok(galleryService.getGalleriesByTailor(tailorId));
        }
}

