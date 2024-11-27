package com.stephenowino.sew_stylesbackend.controller;

import com.stephenowino.sew_stylesbackend.Model.Image;
import com.stephenowino.sew_stylesbackend.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

        private final ImageService imageService;

        public ImageController(ImageService imageService) {
                this.imageService = imageService;
        }

        @PostMapping
        public ResponseEntity<String> uploadImage(@RequestBody Image image) {
                imageService.uploadImage(image);
                return ResponseEntity.ok("Image uploaded successfully!");
        }

        @GetMapping("/gallery/{galleryId}")
        public ResponseEntity<List<Image>> getImagesByGallery(@PathVariable Long galleryId) {
                return ResponseEntity.ok(imageService.getImagesByGallery(galleryId));
        }
}

