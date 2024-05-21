package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.dtos.AuthenticationResponseDto;
import com.b1lbudinhox.wykopclone.dtos.LoginRequestDto;
import com.b1lbudinhox.wykopclone.dtos.RefreshTokenRequestDto;
import com.b1lbudinhox.wykopclone.dtos.RegisterRequestDto;
import com.b1lbudinhox.wykopclone.exceptions.SpringAppException;
import com.b1lbudinhox.wykopclone.models.NotificaitonMail;
import com.b1lbudinhox.wykopclone.models.User;
import com.b1lbudinhox.wykopclone.models.VerificationToken;
import com.b1lbudinhox.wykopclone.repositories.UserRepository;
import com.b1lbudinhox.wykopclone.repositories.VerificationTokenRepository;
import com.b1lbudinhox.wykopclone.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static java.time.LocalTime.now;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    @Transactional
    public void signUp(RegisterRequestDto registerRequestDto){
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEnabled(false);
        user.setCreated(Instant.now());
        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificaitonMail("Prosimy o aktyywację konta ", user.getEmail(),user.getUsername(),
                "Dziękujemy za rejestrację na wykopClone! " +
                "Wciśnij link poniżej aby aktywować swoje konto: " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }
    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    public void verifyAccount(String token) {
        Optional<VerificationToken>  verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringAppException("Invalid Token")));
    }
    private void fetchUserAndEnable(VerificationToken verificationToken){
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringAppException("Couldn't find user with name of: "+username));
        user.setEnabled(true);
        userRepository.save(user);
    }
    public AuthenticationResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginRequestDto.getUsername(), loginRequestDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return AuthenticationResponseDto.builder().authenticationToken(token).
                refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expirationDate(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .build();
    }
    public AuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        refreshTokenService.validateRefreshToken(refreshTokenRequestDto.getRefreshToken());
        String newAuthToken = jwtProvider.generateTokenWithUsername(refreshTokenRequestDto.getUsername());
        String refreshToken = refreshTokenRequestDto.getRefreshToken();
        // String refreshToken = refreshTokenService.enerateRefreshToken(refreshTokenRequest.getUsername()).getToken();
        refreshTokenService.deleteRefreshToken(refreshTokenRequestDto.getRefreshToken());
        return AuthenticationResponseDto.builder()
                .authenticationToken(newAuthToken)
                .refreshToken(refreshToken)
                .expirationDate(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequestDto.getUsername())
                .build();
    }
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }
}
