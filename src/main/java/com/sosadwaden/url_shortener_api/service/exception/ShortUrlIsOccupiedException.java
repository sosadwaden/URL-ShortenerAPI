package com.sosadwaden.url_shortener_api.service.exception;

public class ShortUrlIsOccupiedException extends RuntimeException {
    public ShortUrlIsOccupiedException(String message) {
        super(message);
    }
}
