package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Location;
import ch.zli.m223.punchclock.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location create(Location location) {
        if (location.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameter ID muss null sein.");
        }
        return this.locationRepository.saveAndFlush(location);
    }
    public Location update(Location location) {
        if (!this.locationRepository.existsById(location.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kein Standort mit der ID " + location.getId() + " gefunden.");
        }

        return this.locationRepository.saveAndFlush(location);
    }

    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    public void delete(Long id) {
        this.locationRepository.deleteById(id);
    }
}
