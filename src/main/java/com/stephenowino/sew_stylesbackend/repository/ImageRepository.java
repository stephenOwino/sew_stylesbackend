package com.stephenowino.sew_stylesbackend.repository;


import com.stephenowino.sew_stylesbackend.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
        List<Image> findByGalleryId(Long galleryId); // Fetch all images in a gallery
}

