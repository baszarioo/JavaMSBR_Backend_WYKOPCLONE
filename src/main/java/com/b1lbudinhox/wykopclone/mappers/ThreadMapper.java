package com.b1lbudinhox.wykopclone.mappers;

//import com.b1lbudinhox.wykopclone.dtos;
import com.b1lbudinhox.wykopclone.dtos.ThreadDto;
//import com.b1lbudinhox.wykopclone.models;
import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.models.Thread;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThreadMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(thread.getPosts()))")
    ThreadDto mapThreadToDto(Thread thread);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    Thread mapDtoToThread(ThreadDto threadDto);
}
