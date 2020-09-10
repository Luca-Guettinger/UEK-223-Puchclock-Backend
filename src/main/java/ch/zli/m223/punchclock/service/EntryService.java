package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    private UserService userService;

    public EntryService(EntryRepository entryRepository, UserService userService) {
        this.entryRepository = entryRepository;
        this.userService = userService;
    }

    public long count() {
        return this.entryRepository.count();
    }

    public Entry createEntry(Entry entry, String username) {
        if (entry.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameter ID muss null sein.");
        }
        if (entry.getUser() == null) {
            if (username == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Username muss angegeben werden, wenn im Entry kein Benutzer hinterlegt ist.");
            }
            entry.setUser(userService.loadApplicationUserByUsername(username));
        }
        if (entry.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kategorie muss gesetzt sein.");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kein Eintrag mit der ID " + entry.getId() + " gefunden.");
        }

        this.entryRepository.saveAndFlush(entry);
    }
}
