package com.example.person.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

    public WeatherService(RestTemplate restTemplate, @Value("${api.weather.url}") String apiUrl, @Value("${api.weather.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public String getWeather(String location) {
        String url = apiUrl + "?q=" + location + "&appid=" + apiKey;

        try {
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            if (response != null) {
                return "The weather in " + location + " is " + response.getWeather().getDescription() + " " + response.getWeather().getEmoji();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Failed to retrieve weather information for " + location + " ❌";
    }
}