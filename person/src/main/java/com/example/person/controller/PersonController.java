package com.example.person.controller;

import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable int id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            String location = person.get().getLocation();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/location/{location}", String.class, location);
            return response;
        } else {
            return new ResponseEntity<>("Person not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(person), HttpStatus.CREATED);
    }
}
