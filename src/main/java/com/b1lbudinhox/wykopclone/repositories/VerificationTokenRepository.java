package com.b1lbudinhox.wykopclone.repositories;

import com.b1lbudinhox.wykopclone.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
//    Optional<VerificationToken> deleteVerificationTokenByName(String token);
}
