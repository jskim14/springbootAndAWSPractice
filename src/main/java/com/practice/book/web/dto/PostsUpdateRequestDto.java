package com.practice.book.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
