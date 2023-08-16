package com.sosadwaden.url_shortener_api.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongUrlException extends RuntimeException {

    public WrongUrlException(String message) {
        super(message);
    }
}
