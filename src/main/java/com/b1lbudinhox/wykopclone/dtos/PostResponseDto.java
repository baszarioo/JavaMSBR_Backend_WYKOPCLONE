package com.b1lbudinhox.wykopclone.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String postName;
    private String url;
    private String description;
    private String username;
    private String threadName;
    private String duration;
    private boolean upVote;
    private boolean downVote;
    private Integer voteCount;
    private Integer commentCount;
}
