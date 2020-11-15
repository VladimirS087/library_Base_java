package controllers;

import model.Book;
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
    public List <Man> post(@RequestBody Man man) {
        return manService.post(man);
    }

    @GetMapping(value = "/mans")
    public List<Man> get() {
        return manService.get();
    }

    @GetMapping(value = "/mans/{firstName}")
    public List <Man> getFirstName(@PathVariable(name = "firstName") String firstName) {
        return manService.getFirstName(firstName);
    }

    @DeleteMapping(value = "/mans/{lastName}")
    public ResponseEntity<?> deleteFullName(@RequestParam String firstName, String lastName, String middleName) {
        manService.deleteFullName(firstName, lastName, middleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
