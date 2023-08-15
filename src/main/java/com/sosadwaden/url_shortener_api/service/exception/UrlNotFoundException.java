package com.sosadwaden.url_shortener_api.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UrlNotFoundException extends RuntimeException {

    public UrlNotFoundException(String shortUrl) {
        super(String.format("url %s not found", shortUrl));
    }
}
