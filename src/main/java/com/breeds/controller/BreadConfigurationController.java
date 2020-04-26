package com.breeds.controller;

import com.breeds.dto.BreedDto;
import com.breeds.rest.BreedsRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* The concept of this controller is, that this will usually not exposed
to client and just we have to consume in our application ---
 we can add cross origin filter to block the expose of this controller*/

@RestController
@RequestMapping("/api/v1/configuration")
@Slf4j
public class BreadConfigurationController {

    private BreedsRest breedsRest;

    public BreadConfigurationController(BreedsRest breedsRest) {
        this.breedsRest = breedsRest;
    }

    @GetMapping("/{breed}")
    public BreedDto getBreedResponse(@PathVariable String breed) {
        log.info("In BreadConfigurationController -> getBreedResponse() Called | breed {}", breed);
        return breedsRest.getBreedResponse(breed);
    }
}
