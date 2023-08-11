package com.sosadwaden.url_shortener_api.service;

import org.springframework.stereotype.Service;

@Service
public class BaseConversionService {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] characters = alphabet.toCharArray();
    private int base = characters.length;

    /**
     * Принимает id и возвращает короткую ссылку
     * @param input
     * @return encodedString
     */
    public String encode(long input) {
        StringBuilder encodedString = new StringBuilder();

        if (input == 0) {
            return String.valueOf(characters[0]);
        }

        while (input > 0) {
            encodedString.append(characters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    /**
     * Принимает короткую ссылку и возвращает id.
     * @param input
     * @return String
     */
    public long decode(String input) {
        char[] characters = input.toCharArray();
        int length = characters.length;
        int decoded = 0;
        int counter = 1;

        for (int i = 0; i < length; i++) {
            decoded += alphabet.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }

        return decoded;
    }
}
