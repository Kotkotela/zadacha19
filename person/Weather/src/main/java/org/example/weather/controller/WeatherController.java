package org.example.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final String WEATHER_API_KEY = "688ad16d0a70af2b0104911a47817045";
    private final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}&units=metric";

    private final RestTemplate restTemplate;

    public WeatherController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<String> getWeather(@RequestParam("lat") double latitude, @RequestParam("lon") double longitude) {
        String weatherApiUrl = WEATHER_API_URL.replace("{lat}", Double.toString(latitude))
                .replace("{lon}", Double.toString(longitude))
                .replace("{apiKey}", WEATHER_API_KEY);

        ResponseEntity<String> response = restTemplate.getForEntity(weatherApiUrl, String.class);
        return response;
    }
}