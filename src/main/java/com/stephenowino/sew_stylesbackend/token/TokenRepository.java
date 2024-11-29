package com.stephenowino.sew_stylesbackend.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<VerificationToken,Long> {
        VerificationToken findByToken(String token);
}



