package com.example.person.location;

import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private LocationService locationService;

    @GetMapping("/{location}")
    public Iterable<Person> findByLocation(@PathVariable String location) {
        // Получить фактическое местоположение на основе имени или адреса
        String actualLocation = locationService.getActualLocation(location);
        // Использовать фактическое местоположение для поиска персон
        return repository.findByLocation(actualLocation);
    }
}
