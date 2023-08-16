package com.sosadwaden.url_shortener_api.service;

import com.sosadwaden.url_shortener_api.service.exception.Base62Exception;

public class Base62 {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = 62;

    /**
     * Принимает id и возвращает короткую ссылку
     * @param input
     * @return Encoded string
     */
    public static String encode(long input) {

        if (input < 0) {
            String message = String.format("input must not be negative, have '%d'", input);
            throw new Base62Exception(message);
        }

        if (input == 0) {
            return "a";
        }

        StringBuilder encodedString = new StringBuilder();

        while (input != 0) {
            encodedString.append(ALPHABET.charAt((int) (input % BASE)));
            input /= BASE;
        }

        return encodedString.reverse().toString();
    }

    /**
     * Принимает ссылку и возвращает id.
     * @param input
     * @return long id
     */
    public static long decode(String input) {
        long decoded = 0;

        for (char ch: input.toCharArray()) {
            decoded *= BASE;
            long current = ALPHABET.indexOf(ch);

            if (current == -1) {
                String message = String.format("string '%s' is not a base62 number", input);
                throw new Base62Exception(message);
            }
            decoded += current;

        }

        return decoded;
    }
}
