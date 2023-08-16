package com.sosadwaden.url_shortener_api.service;

import com.sosadwaden.url_shortener_api.entity.dao.Url;
import com.sosadwaden.url_shortener_api.entity.dto.UrlLongRequestDto;
import com.sosadwaden.url_shortener_api.repository.UrlRepository;
import com.sosadwaden.url_shortener_api.service.exception.ShortUrlIsOccupiedException;
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

    public String getOriginalUrl(String shortUrl) {
        long id = Base62.decode(shortUrl);
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
            String message = String.format("Incorrect url '%s'", dto.getLongUrl());
            throw new WrongUrlException(message);
        }

        long id = urlRepository.findFirstMinId();

        Url url = new Url();
        url.setId(id);
        url.setLongUrl(dto.getLongUrl());
        url.setExpirationDate(dto.getExpirationDate());
        url.setCreatedDate(new Date());

        String shortUrl = Base62.encode(id);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);
        return shortUrl;
    }

    public String setShortUrl(UrlLongRequestDto dto) {

        if (!UrlValidation.validate(dto.getLongUrl())) {
            String message = String.format("Incorrect url '%s'", dto.getLongUrl());
            throw new WrongUrlException(message);
        }

        Url url = new Url();
        url.setLongUrl(dto.getLongUrl());
        url.setExpirationDate(dto.getExpirationDate());
        url.setCreatedDate(new Date());

        long id = Base62.decode(dto.getShortUrl());

        if (urlRepository.findById(id).isPresent()) {
            String message = "This short url already use";
            throw new ShortUrlIsOccupiedException(message);
        }

        url.setId(id);
        url.setShortUrl(dto.getShortUrl());
        urlRepository.save(url);

        return url.getShortUrl();
    }
}
