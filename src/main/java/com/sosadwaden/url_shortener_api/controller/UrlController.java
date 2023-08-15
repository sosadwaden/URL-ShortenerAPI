package com.sosadwaden.url_shortener_api.controller;

import com.sosadwaden.url_shortener_api.entity.dto.UrlLongRequestDto;
import com.sosadwaden.url_shortener_api.service.UrlService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;

    @ApiOperation(value = "Convert new URL", notes = "Converts a long URL to a short one")
    @Cacheable(value = "URLs", key = "#shortUrl", sync = true) // эта аннотация автоматически сохраняет результат вызова метода в кэше.
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        String url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @ApiOperation(value = "Redirect and short URL", notes = "Returns a long URl from the passed short URL and redirects to this URL")
    @PostMapping("/create-short")
    public String convertToShortUrl(@RequestBody UrlLongRequestDto dto) {
        return urlService.convertToShortUrl(dto);
    }

    @PostMapping("/set-short-url")
    @ApiOperation(value = "Set short url", notes = "Allows you to manually set a short link")
    public String setShortUrlName(@RequestBody UrlLongRequestDto dto) {
        return urlService.setShortUrl(dto);
    }

}

