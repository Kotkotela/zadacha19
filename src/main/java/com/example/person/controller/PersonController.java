package com.example.person.controller;

import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import com.example.person.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(person), HttpStatus.CREATED);

    }
    @GetMapping("/location/{location}")
    public Iterable<Person> findByLocation(@PathVariable String location) {
        return repository.findByLocation(location);
    }
    @GetMapping("/weather/{location}")
    public String getWeatherForLocation(@PathVariable String location) {
        return weatherService.getWeatherForLocation(location);
    }
}
