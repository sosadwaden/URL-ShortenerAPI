package com.sosadwaden.url_shortener_api.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UrlGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UrlIncorrectData> urlNotFoundExceptionHandle(UrlNotFoundException exception) {
        UrlIncorrectData data = new UrlIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UrlIncorrectData> wrongUrlExceptionHandle(WrongUrlException exception) {
        UrlIncorrectData data = new UrlIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
