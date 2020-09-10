package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
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

    /**
     * @return eine Liste aller aktuell existierenden {@link Location} Objekten.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Location> getAll() {
        return this.locationService.findAll();
    }

    /**
     * @param location Der Standort der gespeichert werden soll, alle eigenschaften ausser ID sind Pflicht, ID **muss** null sein,
     * @return Das in der Datenbank gespeicherten {@link Location} Objket mit generierter ID.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@Valid @RequestBody Location location) {
        return this.locationService.create(location);
    }

    /**
     * @param id die ID des {@link Location} Objektes das gelöscht werden soll.
     * @throws ResponseStatusException wenn das {@link Location} Objekt nicht gelöscht werden kann, da andere Objekte damit verknüpft sind.
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws ResponseStatusException {
        try {
            this.locationService.delete(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        }
    }

    /**
     * @param location das zu editierende {@link Entry} Objekt, ID muss gesetzt sein.
     * @return das bearbeitete {@link Entry} Objekt.
     */
    @PutMapping()
    public Location update(@Valid @RequestBody Location location) {
        return locationService.update(location);
    }
}
