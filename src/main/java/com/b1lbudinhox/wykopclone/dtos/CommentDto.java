package com.b1lbudinhox.wykopclone.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private Long postId;
    private Instant creationDate;
    @NotBlank
    private String text;
    private String username;
}
