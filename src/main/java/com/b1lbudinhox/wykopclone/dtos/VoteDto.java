package com.b1lbudinhox.wykopclone.dtos;

import com.b1lbudinhox.wykopclone.models.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private Long postId;
    private VoteType voteType;

}
