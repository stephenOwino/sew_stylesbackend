package com.stephenowino.sew_stylesbackend.services;

import com.stephenowino.sew_stylesbackend.Model.Comment;
import com.stephenowino.sew_stylesbackend.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

        private final CommentRepository commentRepository;

        public CommentService(CommentRepository commentRepository) {
                this.commentRepository = commentRepository;
        }

        public void addComment(Comment comment) {
                commentRepository.save(comment);
        }

        public List<Comment> getCommentsByImage(Long imageId) {
                return commentRepository.findByImageId(imageId);
        }
}

