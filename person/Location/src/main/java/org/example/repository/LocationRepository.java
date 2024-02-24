package org.example.repository;

import org.example.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
}