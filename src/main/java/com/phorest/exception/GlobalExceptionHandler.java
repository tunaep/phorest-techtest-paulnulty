package com.phorest.exception;

import com.phorest.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {


   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleException(Exception e) {
      ErrorResponse errorResponse = new ErrorResponse(
            Instant.now().toString(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred"
      );
      return ResponseEntity.status(500).body(errorResponse);
   }

}
