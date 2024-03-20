package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.dtos.PostRequestDto;
import com.b1lbudinhox.wykopclone.dtos.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PostService {
    public void save(PostRequestDto postRequestDto) {

    }
    public List<PostResponseDto> getPosts() {
        return Collections.emptyList();
    }
    public PostResponseDto getPost(Long id) {
        return new PostResponseDto();
    }
    public List<PostResponseDto> getPostsByThread(Long threadId) {
        return Collections.emptyList();
    }
    public List<PostResponseDto> getPostsByUsername(String username) {
        return Collections.emptyList();
    }
}
