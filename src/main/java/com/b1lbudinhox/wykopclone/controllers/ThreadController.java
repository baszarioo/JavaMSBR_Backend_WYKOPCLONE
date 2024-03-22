package com.b1lbudinhox.wykopclone.controllers;

import com.b1lbudinhox.wykopclone.services.ThreadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thread")
@AllArgsConstructor
public class ThreadController {
    private final ThreadService threadService;
}
