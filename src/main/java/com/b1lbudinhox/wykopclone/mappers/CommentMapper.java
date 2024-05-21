package com.b1lbudinhox.wykopclone.mappers;

import com.b1lbudinhox.wykopclone.dtos.CommentDto;
import com.b1lbudinhox.wykopclone.models.Comment;
import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target="id", ignore=true)
    @Mapping(target="text", source="commentDto.text")
    @Mapping(target="creationDate", expression="java(java.time.Instant.now())")
    @Mapping(target="post", source="post")
    Comment map(CommentDto commentDto, Post post, User user);

    @Mapping(target="postId", expression="java(comment.getPost().getPostId())")
    @Mapping(target="username", expression="java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}
