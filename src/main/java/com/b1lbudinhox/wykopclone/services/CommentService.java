package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.dtos.CommentDto;
import com.b1lbudinhox.wykopclone.exceptions.NonExistingPostException;
import com.b1lbudinhox.wykopclone.mappers.CommentMapper;
import com.b1lbudinhox.wykopclone.models.Comment;
import com.b1lbudinhox.wykopclone.models.NotificaitonMail;
import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.models.User;
import com.b1lbudinhox.wykopclone.repositories.CommentRepository;
import com.b1lbudinhox.wykopclone.repositories.PostRepository;
import com.b1lbudinhox.wykopclone.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CommentService {
    private static final String POST_URL = "";  /* idk */
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    public void save(CommentDto commentDto) {

    }
    public void createComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new NonExistingPostException(commentDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);
        String message = mailContentBuilder.build(post.getUser().getUsername() +
                " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }
    public List<CommentDto> getCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NonExistingPostException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
    public List<CommentDto> getCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
//        return Collections.emptyList();
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificaitonMail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }
}
