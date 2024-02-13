package com.example.person.location;

import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final PersonRepository personRepository;
    private final LocationService locationService;

    public LocationController(PersonRepository personRepository, LocationService locationService) {
        this.personRepository = personRepository;
        this.locationService = locationService;
    }

    @GetMapping("/{location}")
    public List<Person> getPersonsByLocation(@PathVariable String location) {
        String actualLocation = locationService.getActualLocation(location);
        return personRepository.findByLocation(actualLocation);
    }
}