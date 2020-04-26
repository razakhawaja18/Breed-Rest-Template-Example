package com.breeds.rest;

import com.breeds.restimpl.BreedsRestImpl;
import com.breeds.util.BreedMockFactory;
import com.breeds.util.BreedTestConstant;
import java.util.List;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BreedRestImplTests {

    @Spy
    @InjectMocks
    private BreedsRestImpl breedsRestImpl;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private JSONObject jsonObject;

    @Test
    public void testGetBreedResponse() {
        when(breedsRestImpl.convertJsonIntoList(BreedTestConstant.BREED,
                BreedMockFactory.stringResponseEntityMockForBreeds())).thenReturn(BreedMockFactory.getSubBreedList());
        List<String> subBreeds = breedsRestImpl.convertJsonIntoList(BreedTestConstant.BREED, BreedMockFactory.stringResponseEntityMockForBreeds());
        assertEquals(subBreeds.size(), 3);
        when(breedsRestImpl.convertJsonIntoList(null,
                BreedMockFactory.stringResponseEntityMockForImages())).thenReturn(BreedMockFactory.getSubBreedList());
        List<String> imageList = breedsRestImpl.convertJsonIntoList(null, BreedMockFactory.stringResponseEntityMockForImages());
        assertNotNull(imageList);
    }

}

