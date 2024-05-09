package com.b1lbudinhox.wykopclone.controllers;

import com.b1lbudinhox.wykopclone.dtos.ThreadDto;
import com.b1lbudinhox.wykopclone.dtos.VoteDto;
import com.b1lbudinhox.wykopclone.services.ThreadService;
import com.b1lbudinhox.wykopclone.services.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thread")
@AllArgsConstructor
@Slf4j
public class ThreadController {
    private final ThreadService threadService;
    @PostMapping
    public ResponseEntity<ThreadDto> createThread(@RequestBody ThreadDto threadDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(threadService.save(threadDto));
    }
    @GetMapping
    public ResponseEntity<List<ThreadDto>> getAllThreads() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(threadService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> getThread(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(threadService.getThread(id));
    }

    @RestController
    @RequestMapping("/api/votes")
    @AllArgsConstructor
    public static class VoteController {
        private final VoteService voteService;
        @PostMapping
        public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
            voteService.vote(voteDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
