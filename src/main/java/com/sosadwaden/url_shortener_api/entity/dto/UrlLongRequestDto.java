package com.sosadwaden.url_shortener_api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UrlLongRequestDto {

    private String longUrl;

    private Date expirationDate;
}
