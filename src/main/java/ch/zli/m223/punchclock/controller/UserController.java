package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Location;
import ch.zli.m223.punchclock.service.UserService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * @return eine Liste aller aktuell existierenden {@link ApplicationUser} Objekten.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationUser> getAll() {
        return this.userService.findAll();
    }

    /**
     * @param user das {@link ApplicationUser} Objekt die gespeichert werden soll, alle eigenschaften ausser ID sind Pflicht, ID **muss** null sein,
     * @return Die in der Datenbank gespeicherten {@link Category} mit generierter ID.
     */
    @PostMapping
    public ApplicationUser create(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.save(user);

    }

    /**
     * @param user das zu editierende {@link ApplicationUser} Objekt, ID muss gesetzt sein.
     * @return das bearbeitete {@link ApplicationUser} Objekt.
     */
    @PutMapping
    public ApplicationUser update(@RequestBody ApplicationUser user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.update(user);

    }

    /**
     * Falls sich ein Benutzername ändert Funktioniert diese Funktion nicht mehr, obwohl der Token technisch gesehen noch valide ist.
     * @return der Benutzer für den das aktuelle Token ausgestellt wurde.
     */
    @GetMapping("/me")
    public ApplicationUser getMyself() {
        return this.userService.getMyself();
    }

    /**
     * @param id die ID des {@link ApplicationUser} Objektes das gelöscht werden soll.
     * @throws ResponseStatusException wenn das {@link ApplicationUser} Objekt nicht gelöscht werden kann, da andere Objekte damit verknüpft sind.
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws ResponseStatusException {
        try {
            this.userService.delete(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        }
    }
}
