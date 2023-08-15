package com.sosadwaden.url_shortener_api.util;

import java.util.regex.Pattern;

/**
 * Класс для валидации URL.
 */
public class UrlValidation {

    public static boolean validate(String url) {
        String regex = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][:blank:])?";
        return Pattern.matches(regex, url);
    }
}
