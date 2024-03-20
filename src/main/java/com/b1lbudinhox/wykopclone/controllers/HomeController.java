package com.b1lbudinhox.wykopclone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/all")
    public ModelAndView listAllPosts() {
        return new ModelAndView("");
    }
}
