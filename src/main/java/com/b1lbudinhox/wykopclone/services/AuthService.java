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
    @Transactional
    public void signUp(RegisterRequestDto registerRequestDto){
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEnabled(false);
        user.setRegistered(Instant.now());
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
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),
//                        loginRequestDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthenticationResponseDto();
    }
    public AuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
//        TODO: Implement valid refreshtoken functionality.
        return new AuthenticationResponseDto();
    }
}
