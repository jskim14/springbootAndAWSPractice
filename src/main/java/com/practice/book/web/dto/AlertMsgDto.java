package com.practice.book.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlertMsgDto {
    private String message;
    private String redirectUrl;
}
