package com.sosadwaden.url_shortener_api.controller;

import com.sosadwaden.url_shortener_api.entity.dto.ErrorResponse;
import com.sosadwaden.url_shortener_api.service.exception.ShortUrlIsOccupiedException;
import com.sosadwaden.url_shortener_api.service.exception.UrlNotFoundException;
import com.sosadwaden.url_shortener_api.service.exception.WrongUrlException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class UrlGlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> urlNotFoundExceptionHandler(HttpServletRequest request, Exception exception) {
        return createErrorResponse(request, NOT_FOUND, exception);
    }

    @ExceptionHandler(WrongUrlException.class)
    public ResponseEntity<ErrorResponse> wrongUrlExceptionHandler(HttpServletRequest request, Exception exception) {
        return createErrorResponse(request, BAD_REQUEST, exception);
    }

    @ExceptionHandler(ShortUrlIsOccupiedException.class)
    public ResponseEntity<ErrorResponse> shortUrlIsOccupiedExceptionHandler(HttpServletRequest request, Exception exception) {
        return createErrorResponse(request, CONFLICT, exception);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(
            HttpServletRequest request,
            HttpStatus status,
            Exception exception) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(
                        request.getRequestURL().toString(),
                        exception.getMessage(),
                        status.value()
        ));
    }

}
