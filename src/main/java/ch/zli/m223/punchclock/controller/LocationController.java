package ch.zli.m223.punchclock.controller;


import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Location;
import ch.zli.m223.punchclock.service.LocationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Location> getAll() {
        return this.locationService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@Valid @RequestBody Location location) {
        return this.locationService.create(location);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        try {
            this.locationService.delete(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        }
    }

    @PutMapping()
    public Location update(@Valid @RequestBody Location category) {
        return  locationService.update(category);
    }
}
