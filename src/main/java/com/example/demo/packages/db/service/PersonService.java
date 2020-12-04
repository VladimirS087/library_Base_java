package com.example.demo.packages.db.service;

import com.example.demo.packages.db.dto.PersonDTO;
import com.example.demo.packages.db.entity.Book;

import java.util.Set;

public interface PersonService {

    PersonDTO addPerson(PersonDTO personDTO);

    PersonDTO changePerson(Long id, PersonDTO personDTO);

    void deleteById(Long id);

    void deleteByFullName(String firstName, String lastName, String middleName);

    Set<Book> personBooks(Long id);

    String personTookBook(Long id, Book book);

    String personReturnedBook(Long id, Book book);
}
