package com.example.person;


import com.example.person.weather.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherService weatherService(RestTemplate restTemplate, @Value("${api.weather.url}") String apiUrl, @Value("${api.weather.key}") String apiKey) {
        return new WeatherService(restTemplate, apiUrl, apiKey);
    }
}
