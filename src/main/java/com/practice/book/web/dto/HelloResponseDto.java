package com.practice.book.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //final이 포함된 생성자
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
