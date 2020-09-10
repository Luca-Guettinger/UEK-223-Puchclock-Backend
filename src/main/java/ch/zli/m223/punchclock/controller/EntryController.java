package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    /**
     * @return Eine Liste aller {@link Entry} Objekten.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    /**
     * @return die Anzahl an {@link Entry} Objekten im System.
     */
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countEntries() {
        return entryService.count();
    }

    /**
     * @param entry der Eintrag der gespeichert werden soll, alle eigenschaften ausser ID sind Pflicht, ID **muss** null sein,
     * @return Das in der Datenbank gespeicherten {@link Entry} Objket mit generierter ID.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * @param id die ID des {@link Entry} Objektes das gelöscht werden soll.
     */
    @DeleteMapping("{id}")
    public void deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
    }

    /**
     * @param entry das zu editierende {@link Entry} Objekt, ID muss gesetzt sein.
     * @return das bearbeitete {@link Entry} Objekt.
     */
    @PutMapping("{id}")
    public Entry updateObject(@Valid @RequestBody Entry entry) {
        return entryService.updateEntry(entry);
    }
}
