package com.breeds.restimpl;

import com.breeds.dto.BreedDto;
import com.breeds.dto.ImageDto;
import com.breeds.rest.BreedsRest;
import com.breeds.util.BreedConstants;
import com.breeds.util.BreedUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/* concept of this rest impl class is to call the external
rest api and provide response to caller of this restImpl */

@Service
@Slf4j
public class BreedsRestImpl implements BreedsRest {

    private final RestTemplate restTemplate;
    @Value("${list.all.breeds.url}")
    private String listAllBreedUrl;
    @Value("${list.breeds.images.url}")
    private String listBreedImagesUrl;

    public BreedsRestImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BreedDto getBreedResponse(String breed) {
        log.info("In BreedsRestImpl -> getBreedResponse() Called | breed {}", breed);
        return new BreedDto(breed,
                convertJsonIntoList(breed, getBreedMessageResponse()),
                getImagesResponse(breed));
    }

    private ResponseEntity<String> getBreedMessageResponse() {
        log.info("In BreedsRestImpl -> getBreedMessageResponse() Called");
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(String.valueOf(new URI(listAllBreedUrl)), String.class);
        } catch (URISyntaxException exception) {
            log.error("Exception Occurred while calling URL {}" + listAllBreedUrl, exception);
        }
        return response;
    }

    private ResponseEntity<String> configureBreedImages(String breed) {
        log.info("In BreedsRestImpl -> configureBreedImages() Called | breed {}", breed);
        ResponseEntity<String> response = null;
        String paramUrl = BreedUtil.getParametrizeString(listBreedImagesUrl, breed);
        try {
            response = restTemplate.getForEntity(String.valueOf(new URI(paramUrl)), String.class);
        } catch (URISyntaxException exception) {
            log.error("Exception Occurred while calling URL {}" + paramUrl, exception);
        }
        return response;
    }

    private List<ImageDto> getImagesResponse(String breed) {
        log.info("In BreedsRestImpl -> getImagesResponse() Called | breed {}", breed);
        List<String> breedImagesList = convertJsonIntoList(null, configureBreedImages(breed));
        List<ImageDto> imagesList = new ArrayList<>();
        for (String imageUrl : breedImagesList) {
            ImageDto imageDto = new ImageDto();
            imageDto.setUrl(imageUrl);
            imagesList.add(imageDto);
        }
        return imagesList;
    }

    private List<String> convertJsonIntoList(String breed, ResponseEntity<String> response) {
        log.info("In BreedsRestImpl -> convertJsonIntoList() Called | breed {}, response {}", breed, response.getBody());
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray messageArray = null;
        try {
            if (StringUtils.isEmpty(breed)) {
                messageArray = jsonObject.getJSONArray(BreedConstants.MESSAGE);
            } else {
                messageArray = jsonObject.getJSONObject(BreedConstants.MESSAGE).getJSONArray(breed);
            }
        } catch (JSONException exception) {
            log.error("Exception occurred when fetching {} ", breed, exception);
            return new ArrayList<>();
        }
        List<String> messageList = new ArrayList<>();
        messageArray.forEach(message -> messageList.add(message.toString()));
        return messageList;
    }

}
