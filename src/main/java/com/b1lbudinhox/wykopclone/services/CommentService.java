package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.dtos.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    public void save(CommentDto commentDto) {

    }
    public List<CommentDto> getCommentsForPost(Long postId) {
        return Collections.emptyList();
    }

    public List<CommentDto> getCommentsForUser(String username) {
        return Collections.emptyList();
    }

}
