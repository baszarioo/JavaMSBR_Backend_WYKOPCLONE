package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.exceptions.SpringAppException;
import com.b1lbudinhox.wykopclone.models.RefreshToken;
import com.b1lbudinhox.wykopclone.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
    public final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreationDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }
    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringAppException("Invalid refresh token brv"));
    }
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
