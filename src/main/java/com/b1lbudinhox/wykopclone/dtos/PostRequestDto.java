package com.b1lbudinhox.wykopclone.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private Long postId;
    private String threadName;
    @NotBlank(message = "Post Name cannot be empty or null")
    private String postName;
    private String url;
    private String description;
}
