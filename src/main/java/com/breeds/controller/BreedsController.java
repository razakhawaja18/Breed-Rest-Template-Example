package com.breeds.controller;

import com.breeds.dto.BreedDto;
import com.breeds.service.BreedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* The concept of this controller is, that this will be
exposed to client use to call get api with param */

@RestController
@RequestMapping("/api/v1/breeds")
@Slf4j
public class BreedsController {

    private BreedService breedService;

    public BreedsController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/{breed}")
    public BreedDto getBreedMessageByBreed(@PathVariable String breed) {
        log.info("In BreedsController -> getBreedMessageByBreed() Called | breed {}", breed);
        return breedService.getBreedMessageByBreed(breed);
    }
}
