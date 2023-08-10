package com.sosadwaden.url_shortener_api.controller;

import com.sosadwaden.url_shortener_api.entity.dto.UrlLongRequestDto;
import com.sosadwaden.url_shortener_api.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        String url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @PostMapping("/create-short")
    public String convertToShortUrl(@RequestBody UrlLongRequestDto dto) {
        return urlService.convertToShortUrl(dto);
    }
}

