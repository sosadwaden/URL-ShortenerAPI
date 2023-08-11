package com.sosadwaden.url_shortener_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // включение кэширования в SpringBoot
public class UrlShortenerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApiApplication.class, args);
    }

}
