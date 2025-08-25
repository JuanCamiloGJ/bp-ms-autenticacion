package com.crediya.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.crediya.model.user.exceptions.UserAnyException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(WebExchangeBindException ex) {
        var errors = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Bad Request", errors));
    }

    // Puedes agregar otros handlers para tus excepciones personalizadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server Error", ex.getMessage()));
    }

    @ExceptionHandler(UserAnyException.class)
    public ResponseEntity<ErrorResponse> handleUserAnyException(UserAnyException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorResponse("User Error", ex.getMessage()));
    }



    // DTO para la respuesta de error
    public record ErrorResponse(String error, Object details) {
    }

}
