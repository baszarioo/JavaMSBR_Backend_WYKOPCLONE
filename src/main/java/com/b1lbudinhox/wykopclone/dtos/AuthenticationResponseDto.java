package com.b1lbudinhox.wykopclone.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponseDto {
    private String authenticationToken;
    private String username;
    private String refreshToken;
    private Instant expirationDate;
}