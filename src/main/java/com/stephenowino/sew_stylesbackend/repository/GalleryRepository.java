package com.stephenowino.sew_stylesbackend.repository;


import com.stephenowino.sew_stylesbackend.Model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
        List<Gallery> findByTailorId(Long tailorId); // Fetch all galleries of a specific tailor
}

