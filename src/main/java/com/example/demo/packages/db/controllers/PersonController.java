package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Person;
import com.example.demo.packages.db.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class PersonController {
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    // добавляем пользователя
    @PostMapping(value = "post-person")
    public Person postPerson (@RequestBody Person person) {
        return personRepository.save(person);
    }

    // изменяем пользователя
    @PutMapping(value = "change-person/{Id}")
    public Person change(
            @PathVariable("Id") Person personFromDb,
            @RequestBody Person person
    ) {
        BeanUtils.copyProperties(person, personFromDb, "Id");
        return personRepository.save(person);
    }

    // удаляем по Id
    @DeleteMapping(value = "person/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestParam Long Id) {

        personRepository.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // удаляем по ФИО
    @DeleteMapping(value = "delete-by-fullName")
    public ResponseEntity<?> deleteByFullName(@RequestParam String firstName,
                                            @RequestParam String lastName,
                                            @RequestParam String middleName) {
        List<Person> persons = personRepository.findByFullName(firstName, lastName, middleName);
        for (Person person : persons) {
            personRepository.deleteById(person.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //    параметры для примера:
//    ?firstName=Bob&lastName=Marley&middleName=Bob
//    ?firstName=John&lastName=Bon&middleName=Jovi

    // получаем список книг пользователя
    @GetMapping(value = "person-books/{Id}")
    Set<Book> personBooks (@PathVariable("Id") Long Id) {
        Optional<Person> person = personRepository.findById(Id);
        Person realPerson = person.get();
        return realPerson.getBooks();
    }

    // добавляем книгу пользователю
    @PostMapping(value = "person-took-book/{Id}")
    public String personTookBook (
            @PathVariable("Id") Long Id,
            @RequestBody Book book
    ) {
        Optional<Person> person = personRepository.findById(Id);
        Person realPerson = person.get();
        realPerson.addBook(book);
        return (realPerson.toString() + ":" + realPerson.getBooks());
    }
    // удаляем книгу от пользователя
    @PostMapping(value = "person-returned-book/{Id}")
    public String personReturnedBook (
            @PathVariable("Id") Long Id,
            @RequestBody Book book
    ) {
        Optional<Person> person = personRepository.findById(Id);
        Person realPerson = person.get();
        realPerson.removeBook(book);
        return (realPerson.toString() + ":" + realPerson.getBooks());
    }
}
