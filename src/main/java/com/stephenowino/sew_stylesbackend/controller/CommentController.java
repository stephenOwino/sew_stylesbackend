package com.stephenowino.sew_stylesbackend.controller;


import com.stephenowino.sew_stylesbackend.Model.Comment;
import com.stephenowino.sew_stylesbackend.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

        private final CommentService commentService;

        public CommentController(CommentService commentService) {
                this.commentService = commentService;
        }

        @PostMapping
        public ResponseEntity<String> addComment(@RequestBody Comment comment) {
                commentService.addComment(comment);
                return ResponseEntity.ok("Comment added successfully!");
        }

        @GetMapping("/image/{imageId}")
        public ResponseEntity<List<Comment>> getCommentsByImage(@PathVariable Long imageId) {
                return ResponseEntity.ok(commentService.getCommentsByImage(imageId));
        }
}

