package com.stephenowino.sew_stylesbackend.services;


import com.stephenowino.sew_stylesbackend.Model.Follow;
import com.stephenowino.sew_stylesbackend.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

        private final FollowRepository followRepository;

        public FollowService(FollowRepository followRepository) {
                this.followRepository = followRepository;
        }

        public void followTailor(Follow follow) {
                followRepository.save(follow);
        }

        public List<Follow> getFollowersByTailor(Long tailorId) {
                return followRepository.findByTailorId(tailorId);
        }

        public List<Follow> getTailorsFollowedByUser(Long userId) {
                return followRepository.findByFollowerId(userId);
        }
}

