package com.example.person;


import com.example.person.weather.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${api.weather.url}")
    private String apiUrl;

    @Value("${api.weather.key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherService weatherService() {
        return new WeatherService();
    }
}