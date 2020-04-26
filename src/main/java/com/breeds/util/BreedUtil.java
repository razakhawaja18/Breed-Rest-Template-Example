package com.breeds.util;

import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BreedUtil {

    private BreedUtil() {
    }

    public static String getParametrizeString(String url, String param) {
        log.info("In BreedUtil -> getParametrizeString() Called | url {}, param {}", url, param);
        return MessageFormat.format(url, param);
    }
}
