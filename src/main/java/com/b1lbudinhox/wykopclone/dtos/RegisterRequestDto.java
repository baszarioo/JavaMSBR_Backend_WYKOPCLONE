package com.b1lbudinhox.wykopclone.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDto {
    @Email
    @NotEmpty(message = "Email - required")
    private String email;
    @NotBlank(message = "Username - required")
    private String username;
    @NotBlank(message = "Password - required")
    private String password;
}
