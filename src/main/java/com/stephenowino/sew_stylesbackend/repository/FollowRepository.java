package com.stephenowino.sew_stylesbackend.repository;




import com.stephenowino.sew_stylesbackend.Model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
        List<Follow> findByTailorId(Long tailorId); // Fetch all followers of a tailor
        List<Follow> findByFollowerId(Long followerId); // Fetch all tailors followed by a user
}

