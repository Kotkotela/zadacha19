package com.example.person.location;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;
import org.springframework.core.env.Environment;
import java.nio.charset.StandardCharsets;

@Service
public class LocationService {

    private RestTemplate restTemplate;


    @Value("${geocoding.api.url}")
    private String apiUrl;

    public LocationService(RestTemplate restTemplate, @Value("${geocoding.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    public String getActualLocation(String location) {
        String url = apiUrl.replace("{address}", UriUtils.encodePath(location, StandardCharsets.UTF_8));



        try {
            GeoCodeResponse response = restTemplate.getForObject(url, GeoCodeResponse.class);
            if (response != null && response.getResults().length > 0) {
                return response.getResults()[0].getFormattedAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
