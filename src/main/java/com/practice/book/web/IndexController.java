package com.practice.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; //src/main/resources/templates/index.mustache 반환
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
