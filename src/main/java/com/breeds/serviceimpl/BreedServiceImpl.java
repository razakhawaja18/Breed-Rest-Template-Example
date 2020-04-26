package com.breeds.serviceimpl;

import com.breeds.dto.BreedDto;
import com.breeds.service.BreedService;
import com.breeds.util.BreedUtil;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/* concept of this service class is to call the external
rest api and provide response to client exposed controller */

@Service
@Slf4j
public class BreedServiceImpl implements BreedService {

    private final RestTemplate restTemplate;
    @Value("${breed.response.api}")
    private String breedResponseApi;

    public BreedServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BreedDto getBreedMessageByBreed(String breed) {
        log.info("In BreedServiceImpl -> getBreedMessageByBreed() Called | breed {}", breed);
        ResponseEntity<BreedDto> response = null;
        String paramUrl = BreedUtil.getParametrizeString(breedResponseApi, breed);
        try {
            response = restTemplate.getForEntity(String.valueOf(new URI(paramUrl)), BreedDto.class);
        } catch (URISyntaxException exception) {
            log.error("Exception Occurred while calling URL {}" + paramUrl, exception);
        }
        return response != null ? response.getBody() : null;
    }
}
