package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.dto.PersonDTO;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "persons", produces = "application/json")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // добавляем пользователя
    @PostMapping(value = "/add-person")
    public PersonDTO addPerson (@RequestBody PersonDTO personDTO) {
        return personService.addPerson(personDTO);
    }

    // изменяем пользователя
    @PutMapping(value = "/change-person/{id}")
    public PersonDTO changePerson(
            @PathVariable("id") Long id,
            @RequestBody PersonDTO personDTO
    ) {
        return personService.changePerson(id, personDTO);
    }

    // удаляем по id
    @DeleteMapping(value = "/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {

        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // удаляем по ФИО
    @DeleteMapping(value = "/delete-by-fullName")
    public ResponseEntity<?> deleteByFullName(@RequestParam String firstName,
                                            @RequestParam String lastName,
                                            @RequestParam String middleName) {
        personService.deleteByFullName(firstName, lastName, middleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // получаем список книг пользователя
    @GetMapping(value = "/list-books/{id}")
    public Set<Book> personBooks (@PathVariable("id") Long id) {
        return personService.personBooks(id);
    }

    // добавляем книгу пользователю
    @PostMapping(value = "/person-took-book/{id}")
    public String personTookBook (
            @PathVariable("id") Long id,
            @RequestBody Book book
    ) {
        return personService.personTookBook(id, book);
    }

    // удаляем книгу от пользователя
    @PostMapping(value = "/person-returned-book/{id}")
    public String personReturnedBook (
            @PathVariable("id") Long id,
            @RequestBody Book book
    ) {
        return personService.personReturnedBook(id, book);
    }
}
