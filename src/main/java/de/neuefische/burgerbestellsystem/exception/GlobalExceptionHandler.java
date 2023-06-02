package de.neuefische.burgerbestellsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMenuNotFoundException(MenuNotFoundException exception) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", exception.getMessage());
        response.put("timestamp", Instant.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
