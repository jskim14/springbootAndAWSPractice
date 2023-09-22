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

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<CommonResponse<?>> constraintViolationHandler(ConstraintViolationException exception, WebRequest request){
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, exception.getConstraintViolations());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.fail(errorResponse));
    }
}
