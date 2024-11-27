package com.stephenowino.sew_stylesbackend.controller;


import com.stephenowino.sew_stylesbackend.Model.Follow;
import com.stephenowino.sew_stylesbackend.services.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

        private final FollowService followService;

        public FollowController(FollowService followService) {
                this.followService = followService;
        }

        @PostMapping
        public ResponseEntity<String> followTailor(@RequestBody Follow follow) {
                followService.followTailor(follow);
                return ResponseEntity.ok("Followed tailor successfully!");
        }

        @GetMapping("/tailor/{tailorId}")
        public ResponseEntity<List<Follow>> getFollowersByTailor(@PathVariable Long tailorId) {
                return ResponseEntity.ok(followService.getFollowersByTailor(tailorId));
        }
}

