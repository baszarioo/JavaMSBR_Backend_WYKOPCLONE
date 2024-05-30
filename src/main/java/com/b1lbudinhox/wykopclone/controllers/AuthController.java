package com.b1lbudinhox.wykopclone.controllers;

import com.b1lbudinhox.wykopclone.dtos.AuthenticationResponseDto;
import com.b1lbudinhox.wykopclone.dtos.LoginRequestDto;
import com.b1lbudinhox.wykopclone.dtos.RefreshTokenRequestDto;
import com.b1lbudinhox.wykopclone.dtos.RegisterRequestDto;
import com.b1lbudinhox.wykopclone.services.AuthService;
import com.b1lbudinhox.wykopclone.services.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequestDto registerRequestDto) {
        authService.signUp(registerRequestDto);
        return new ResponseEntity<>("User Registration Successful", OK);
    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }
    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequestDto.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted ...");
    }
    @PostMapping("/refresh/token")
    public AuthenticationResponseDto refreshTokens(@Valid @RequestBody
                                                       RefreshTokenRequestDto refreshTokenRequestDto) {
        return authService.refreshToken(refreshTokenRequestDto);
    }
}
