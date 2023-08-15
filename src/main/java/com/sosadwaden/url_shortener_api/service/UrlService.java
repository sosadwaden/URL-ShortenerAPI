package com.sosadwaden.url_shortener_api.service;

import com.sosadwaden.url_shortener_api.entity.dao.Url;
import com.sosadwaden.url_shortener_api.entity.dto.UrlLongRequestDto;
import com.sosadwaden.url_shortener_api.repository.UrlRepository;
import com.sosadwaden.url_shortener_api.service.exception.UrlNotFoundException;
import com.sosadwaden.url_shortener_api.service.exception.WrongUrlException;
import com.sosadwaden.url_shortener_api.util.UrlValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final BaseConversionService baseConversionService;

    public String getOriginalUrl(String shortUrl) {
        long id = baseConversionService.decode(shortUrl);
        Url url = urlRepository
                .findById(id)
                .orElseThrow(() -> new UrlNotFoundException(shortUrl));

        if (url.getExpirationDate() != null && url.getExpirationDate().before(new Date())) {
            urlRepository.delete(url);
            throw new EntityNotFoundException("Link expired!");
        }

        return url.getLongUrl();
    }

    public String convertToShortUrl(UrlLongRequestDto dto) {

        if (!UrlValidation.validate(dto.getLongUrl())) {
            throw new WrongUrlException(dto.getLongUrl());
        }

        Url url = new Url();
        url.setLongUrl(dto.getLongUrl());
        url.setExpirationDate(dto.getExpirationDate());
        url.setCreatedDate(new Date());

        Url entity = urlRepository.save(url);

        String shortUrl = baseConversionService.encode(entity.getId());
        entity.setShortUrl(shortUrl);
        urlRepository.save(entity);
        return shortUrl;
    }

    public String setShortUrl(UrlLongRequestDto dto) {
        // TODO Сделать проверку на то, что в БД есть/нет такого короткого Url
        Url url = new Url();
        url.setLongUrl(dto.getLongUrl());
        url.setExpirationDate(dto.getExpirationDate());
        url.setCreatedDate(new Date());

        long id = baseConversionService.decode(dto.getShortUrl());

        url.setId(id);
        url.setShortUrl(dto.getShortUrl());
        urlRepository.save(url);

        return url.getShortUrl();
    }
}
