package com.practice.book.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private T data;
    private ErrorResponse error;

    public CommonResponse(T data) {
        this.data = data;
    }

    public CommonResponse(ErrorResponse error) {
        this.error = error;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(data);
    }

    public static CommonResponse<?> fail(ErrorResponse errorResponse) {
        return new CommonResponse<>(errorResponse);
    }
}
