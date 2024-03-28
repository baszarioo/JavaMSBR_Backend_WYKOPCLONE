package com.b1lbudinhox.wykopclone.controllers;

import com.b1lbudinhox.wykopclone.dtos.RegisterRequestDto;
import com.b1lbudinhox.wykopclone.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequestDto registerRequestDto) {
        authService.signUp(registerRequestDto);
        return new ResponseEntity<>("User Registration Successful", OK);
    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity(OK);
    }
}
