package com.sosadwaden.url_shortener_api.service.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JSON, который отображается при возникновении исключительных ситуаций
 */
@Getter
@Setter
@NoArgsConstructor
public class UrlIncorrectData {
    private String info;
}
