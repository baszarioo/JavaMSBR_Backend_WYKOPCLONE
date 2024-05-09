package com.b1lbudinhox.wykopclone.services;

import com.b1lbudinhox.wykopclone.dtos.ThreadDto;
import com.b1lbudinhox.wykopclone.exceptions.SpringAppException;
import com.b1lbudinhox.wykopclone.models.Thread;
import com.b1lbudinhox.wykopclone.repositories.ThreadRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ThreadService {
    private final ThreadRepository threadRepository;
    private final com.b1lbudinhox.wykopclone.mappers.ThreadMapper threadMapper;

    @Transactional
    public ThreadDto save(ThreadDto threadDto){
        Thread save = threadRepository.save(threadMapper.mapDtoToThread(threadDto));
        threadDto.setId(save.getId());
        return threadDto;
    }

    @Transactional(readOnly = true)
    public List<ThreadDto> getAll() {
        return threadRepository.findAll()
                .stream()
                .map(threadMapper::mapThreadToDto)
                .collect(Collectors.toList());
    }
    public ThreadDto getThread(Long id) {
        Thread thread = threadRepository.findById(id)
                .orElseThrow(() -> new SpringAppException("No subreddit found with ID - " + id));
        return threadMapper.mapThreadToDto(thread);
    }
}
