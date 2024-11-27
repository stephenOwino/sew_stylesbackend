package com.stephenowino.sew_stylesbackend.services;

import com.stephenowino.sew_stylesbackend.Model.Image;
import com.stephenowino.sew_stylesbackend.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

        private final ImageRepository imageRepository;

        public ImageService(ImageRepository imageRepository) {
                this.imageRepository = imageRepository;
        }

        public void uploadImage(Image image) {
                imageRepository.save(image);
        }

        public List<Image> getImagesByGallery(Long galleryId) {
                return imageRepository.findByGalleryId(galleryId);
        }
}

