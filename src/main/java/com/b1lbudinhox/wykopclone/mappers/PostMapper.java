package com.b1lbudinhox.wykopclone.mappers;

import com.b1lbudinhox.wykopclone.dtos.PostRequestDto;
import com.b1lbudinhox.wykopclone.dtos.PostResponseDto;
import com.b1lbudinhox.wykopclone.dtos.ThreadDto;
import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.models.Thread;
import com.b1lbudinhox.wykopclone.models.User;
import com.b1lbudinhox.wykopclone.repositories.CommentRepository;
import com.b1lbudinhox.wykopclone.repositories.VoteRepository;
import com.b1lbudinhox.wykopclone.services.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")  //adhering MapStruct usage patterns.
public interface PostMapper {
//    Maps postRequest to Post entity.
    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "thread", source = "thread")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "postRequestDto.description")
    @Mapping(target = "voteCount", constant = "0")
    Post map(PostRequestDto postRequestDto, Thread thread, User user);

//    Func: Maps post entity to PostResponseDto..
    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "threadName", source = "thread.tagName")
    @Mapping(target = "username", source = "user.username")
//    @Mapping(target = "commentCount", source = "java(post.getComments().size())")
    PostResponseDto mapToDto(Post post);
}
