package controllers;

import model.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ManService;

import java.util.List;

@RestController
public class ManController {
    private final ManService manService;

    @Autowired
    public ManController(ManService manService) {
        this.manService = manService;
    }

    @PostMapping(value = "/mans")
    public ResponseEntity<?> post(@RequestBody Man man) {
        manService.post(man);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/mans")
    public ResponseEntity<List<Man>> get() {
        final List<Man> books = manService.get();

        return books != null &&  !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/mans/{id}")
    public ResponseEntity<Man> read(@PathVariable(name = "id") int id) {
        final Man man = manService.read(id);

        return man != null
                ? new ResponseEntity<>(man, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/mans/{firstName}")
    public ResponseEntity<Man> readFirstName(@PathVariable(name = "firstName") String firstName) {
        final Man man = manService.readFirstName(firstName);

        return man != null
                ? new ResponseEntity<>(man, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/mans/{lastName}")
    public ResponseEntity<?> deleteLastName(@PathVariable(name = "lastName") String lastName) {
        final boolean deleted = manService.deleteLastName(lastName);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/books/{firstName}")
    public ResponseEntity<?> deleteFirstName(@PathVariable(name = "firstName") String firstName) {
        final boolean deleted = manService.deleteFirstName(firstName);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/books/{middleName}")
    public ResponseEntity<?> deleteMiddleName(@PathVariable(name = "middleName") String middleName) {
        final boolean deleted = manService.deleteMiddleName(middleName);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
