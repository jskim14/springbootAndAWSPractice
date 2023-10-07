package com.practice.book.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostsDeleteRequestDto {
    private String author;

    @Builder
    public PostsDeleteRequestDto(String author) {
        this.author = author;
    }
}
