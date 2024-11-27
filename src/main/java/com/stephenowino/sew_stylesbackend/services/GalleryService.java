package com.stephenowino.sew_stylesbackend.services;

import com.stephenowino.sew_stylesbackend.Model.Gallery;
import com.stephenowino.sew_stylesbackend.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

        private final GalleryRepository galleryRepository;

        public GalleryService(GalleryRepository galleryRepository) {
                this.galleryRepository = galleryRepository;
        }

        public void createGallery(Gallery gallery) {
                galleryRepository.save(gallery);
        }

        public List<Gallery> getGalleriesByTailor(Long tailorId) {
                return galleryRepository.findByTailorId(tailorId);
        }
}

