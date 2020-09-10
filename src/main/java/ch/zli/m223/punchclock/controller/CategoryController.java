package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @return eine Liste aller aktuell existierenden {@link Category} Objekten.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    /**
     * @param category das {@link Category} Objekt das gespeichert werden soll, name darf nicht null sein, ID muss null sein,
     * @return das in der Datenbank gespeicherten {@link Category} Objekt mit generierter ID.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@Valid @RequestBody Category category) {
        return categoryService.create(category);
    }

    /**
     * @param id die ID des {@link Category} Objektes das gelöscht werden soll.
     * @throws ResponseStatusException wenn das {@link Category} Objekt nicht gelöscht werden kann, da andere Objekte damit verknüpft sind.
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws ResponseStatusException {
        try {
            categoryService.delete(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        }
    }

    /**
     * @param category die zu editierende {@link Category} Objekt, ID muss gesetzt sein.
     * @return das bearbeitete {@link Category} Objekt.
     */
    @PutMapping()
    public Category update(@Valid @RequestBody Category category) {
        return  categoryService.update(category);
    }
}
