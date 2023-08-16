package com.sosadwaden.url_shortener_api.entity.dto;

import lombok.*;

/**
 * JSON, который отображается при возникновении исключительных ситуаций
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String path;
    private String info;
    private int statusCode;
}
