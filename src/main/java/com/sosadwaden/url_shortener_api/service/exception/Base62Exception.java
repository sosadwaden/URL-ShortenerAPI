package com.sosadwaden.url_shortener_api.service.exception;

public class Base62Exception extends RuntimeException {
    public Base62Exception(String message) {
        super(message);
    }
}
