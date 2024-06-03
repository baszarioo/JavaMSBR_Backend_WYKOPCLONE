package com.b1lbudinhox.wykopclone.controllers;

import com.b1lbudinhox.wykopclone.dtos.PostRequestDto;
import com.b1lbudinhox.wykopclone.dtos.PostResponseDto;
import com.b1lbudinhox.wykopclone.services.PostService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
//@NoArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequestDto postRequestDto) {
        postService.save(postRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getPosts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }
    @GetMapping(params = "threadId")
    public ResponseEntity<List<PostResponseDto>> getPostsByThread(@RequestParam Long threadId) {
        return status(HttpStatus.OK).body(postService.getPostsByThread(threadId));
    }
    @GetMapping(params="username")
    public ResponseEntity<List<PostResponseDto>> getPostsByUsername(@RequestParam String usernmae) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(usernmae));
    }
}
