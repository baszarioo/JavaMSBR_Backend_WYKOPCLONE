package com.b1lbudinhox.wykopclone.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThreadDto {
    private Long id;
    @NotBlank(message = "Tag name is required")
    private String tagName;
    @NotBlank(message = "Description is required")
    private String description;
    private Integer numberOfPosts;
}
