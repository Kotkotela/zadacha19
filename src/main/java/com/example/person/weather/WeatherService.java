package com.example.person.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.weather.url}")
    private String apiUrl;

    @Value("${api.weather.key}")
    private String apiKey;

    public String getWeatherForLocation(String location) {
        String url = apiUrl + "?q=" + location + "&appid=" + apiKey;

        try {
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            return "The weather in " + location + " is " + response.getWeather().getDescription() + " " + response.getWeather().getEmoji();
        } catch (Exception e) {
            return "Failed to retrieve weather information for " + location + " ❌";
        }
    }
}