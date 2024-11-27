package com.stephenowino.sew_stylesbackend.repository;

import com.stephenowino.sew_stylesbackend.Model.Tailor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TailorRepository extends JpaRepository<Tailor, Long> {
        Optional<Tailor> findByUserId(Long userId); // To fetch tailor details by user ID
}

