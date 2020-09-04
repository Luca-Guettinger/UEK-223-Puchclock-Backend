package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        if (entry.getId() != null)  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameter ID muss null sein.");
        }
        return entryRepository.saveAndFlush(entry);
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public void deleteEntry(Long id) {
        this.entryRepository.deleteById(id);
    }

    public void updateEntry(Entry entry) {
        if (!this.entryRepository.existsById(entry.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Kein Eintrag mit der ID " + entry.getId() + " gefunden.");
        }

        this.entryRepository.saveAndFlush(entry);
    }
}
