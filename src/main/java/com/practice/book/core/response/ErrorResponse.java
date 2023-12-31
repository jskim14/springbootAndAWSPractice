package com.practice.book.core.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;
    private int status;
    private String title;
    private String detail;

    @Builder
    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.title = httpStatus.name();
        this.detail = message;
    }

    public static ErrorResponse of(HttpStatus httpStatus, Set<ConstraintViolation<?>> violations) {

        return new ErrorResponse(httpStatus, ConstraintViolationError.of(violations));
    }

    private static class ConstraintViolationError {
        public static String of(Set<ConstraintViolation<?>> violations) {

            return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        }
    }
}
