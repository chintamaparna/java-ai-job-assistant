package com.example.aijobassistant.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldHandleResponseStatusException() {

        ResponseStatusException exception =
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found: 99"
                );

        ResponseEntity<Map<String, Object>> response =
                handler.handleResponseStatusException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(
                404,
                response.getBody().get("status")
        );

        assertEquals(
                "Job not found: 99",
                response.getBody().get("error")
        );

        assertNotNull(response.getBody().get("timestamp"));
    }

    @Test
    void shouldHandleIllegalArgumentException() {

        IllegalArgumentException exception =
                new IllegalArgumentException(
                        "Only PDF files are supported."
                );

        ResponseEntity<Map<String, Object>> response =
                handler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(
                400,
                response.getBody().get("status")
        );

        assertEquals(
                "Only PDF files are supported.",
                response.getBody().get("error")
        );
    }
}