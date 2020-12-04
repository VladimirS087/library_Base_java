package com.example.demo.packages.db.service;

import com.example.demo.packages.db.dto.PersonDTO;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Person;
import com.example.demo.packages.db.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDTO addPerson (PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setMiddleName(personDTO.getMiddleName());
        personRepository.save(person);
        return personDTO;
    }

    @Override
    public PersonDTO changePerson(Long id, PersonDTO personDTO) {
        Person person = personRepository.findById(id).get();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setMiddleName(personDTO.getMiddleName());
        BeanUtils.copyProperties(person, personDTO, "id");
        personRepository.save(person);
        return personDTO;
    }

    @Override
    public void deleteById(Long id){
        personRepository.deleteById(id);
    }

    @Override
    public void deleteByFullName(String firstName, String lastName, String middleName) {
        List<Person> persons = personRepository.findByFullName(firstName, lastName, middleName);
        for (Person person : persons) {
            personRepository.deleteById(person.getId());
        }
    }

    @Override
    public Set<Book> personBooks(Long id) {
        Optional<Person> person = personRepository.findById(id);
        Person realPerson = person.get();
        return realPerson.getBooks();
    }

    @Override
    public String personTookBook(Long id, Book book){
        Optional<Person> person = personRepository.findById(id);
        Person realPerson = person.get();
        realPerson.getBooks().add(book);
        return (realPerson.toString() + ":" + realPerson.getBooks());
    }

    @Override
    public String personReturnedBook(Long id, Book book){
        Optional<Person> person = personRepository.findById(id);
        Person realPerson = person.get();
        realPerson.getBooks().remove(book);
        return (realPerson.toString() + ":" + realPerson.getBooks());
    }

}
