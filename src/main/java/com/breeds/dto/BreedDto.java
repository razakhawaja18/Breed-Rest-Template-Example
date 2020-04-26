package com.breeds.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class BreedDto {
    private String breed;
    private List<String> subBreeds;
    private List<ImageDto> images;

    public BreedDto(String breed, List<String> subBreeds, List<ImageDto> images) {
        log.info("In BreedDto -> BreedDto() | breed {}, subBreeds{} , images{}", breed, subBreeds, images);
        this.breed = breed;
        this.subBreeds = subBreeds;
        this.images = images;
    }
}
