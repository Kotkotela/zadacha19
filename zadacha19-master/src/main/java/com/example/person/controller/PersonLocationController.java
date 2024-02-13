package com.example.person.controller;

import com.example.person.location.LocationService;
import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import com.example.person.weather.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person/location")
public class PersonLocationController {

    private final PersonRepository personRepository;
    private final LocationService locationService;

    public PersonLocationController(PersonRepository personRepository, LocationService locationService) {
        this.personRepository = personRepository;
        this.locationService = locationService;
    }

    @GetMapping("/{location}")
    public List<Person> getPersonsByLocation(@PathVariable String location) {
        String actualLocation = locationService.getActualLocation(location);
        return personRepository.findByLocation(actualLocation);
    }
}