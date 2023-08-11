package com.sosadwaden.url_shortener_api.service;

import com.sosadwaden.url_shortener_api.entity.dao.Url;
import com.sosadwaden.url_shortener_api.entity.dto.UrlLongRequestDto;
import com.sosadwaden.url_shortener_api.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final BaseConversionService baseConversionService;

    public String convertToShortUrl(UrlLongRequestDto dto) {
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

    public String getOriginalUrl(String shortUrl) {
        long id = baseConversionService.decode(shortUrl);
        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (url.getExpirationDate() != null && url.getExpirationDate().before(new Date())){
            urlRepository.delete(url);
            throw new EntityNotFoundException("Link expired!");
        }

        return url.getLongUrl();
    }
}
