package com.practice.book.core.exception;

import com.practice.book.core.response.CommonResponse;
import com.practice.book.core.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<CommonResponse<?>> constraintViolationHandler(ConstraintViolationException exception, WebRequest request){
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, exception.getConstraintViolations());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.fail(errorResponse));
    }

    @ExceptionHandler({NoSuchElementException.class})
    public final ResponseEntity<CommonResponse<?>> noSuchElementHandler(RuntimeException exception, WebRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommonResponse.fail(errorResponse));
    }

    @ExceptionHandler({NullPointerException.class})
    public final ResponseEntity<CommonResponse<?>> nullPointHandler(RuntimeException exception, WebRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("NO DATA FOUND")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommonResponse.fail(errorResponse));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public final ResponseEntity<CommonResponse<?>> illegalArgumentExceptionHandler(RuntimeException exception, WebRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommonResponse.fail(errorResponse));
    }
}
