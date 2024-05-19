package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.dtos.PostRequestDto;
import com.b1lbudinhox.wykopclone.dtos.PostResponseDto;
import com.b1lbudinhox.wykopclone.exceptions.NonExistingPostException;
import com.b1lbudinhox.wykopclone.mappers.PostMapper;
import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.repositories.PostRepository;
import com.b1lbudinhox.wykopclone.repositories.ThreadRepository;
import com.b1lbudinhox.wykopclone.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final ThreadRepository threadRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NonExistingPostException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional (readOnly = true)
    public List<PostResponseDto> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
    public void save(PostRequestDto postRequestDto) {

    }
    public List<PostResponseDto> getPostsByThread(Long threadId) {
        return Collections.emptyList();
    }
    public List<PostResponseDto> getPostsByUsername(String username) {
        return Collections.emptyList();
    }
}
