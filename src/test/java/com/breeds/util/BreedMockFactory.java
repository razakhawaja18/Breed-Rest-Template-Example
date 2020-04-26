package com.breeds.util;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BreedMockFactory {

    private BreedMockFactory() {

    }

    public static ResponseEntity<String> stringResponseEntityMockForBreeds() {
        return new ResponseEntity<>(BreedTestConstant.BREEDS_RESPONSE, HttpStatus.OK);
    }

    public static ResponseEntity<String> stringResponseEntityMockForImages() {
        return new ResponseEntity<>(BreedTestConstant.IMAGES_RESPONSE, HttpStatus.OK);
    }

    public static List<String> getSubBreedList() {
        return Arrays.asList("boston", "english", "french");
    }

}
