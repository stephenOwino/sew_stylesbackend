package com.stephenowino.sew_stylesbackend.repository;


import com.stephenowino.sew_stylesbackend.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
        List<Comment> findByImageId(Long imageId); // Fetch all comments for an image
}

