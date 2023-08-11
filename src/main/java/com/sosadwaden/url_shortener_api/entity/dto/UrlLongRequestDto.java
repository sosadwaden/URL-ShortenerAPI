package com.sosadwaden.url_shortener_api.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(description = "DTO object for POST method")
public class UrlLongRequestDto {

    @ApiModelProperty(required = true, notes = "Long URL to convert to short")
    private String longUrl;

    @ApiModelProperty(notes = "Expiration datetime of url")
    private Date expirationDate;
}
