package com.example.demo.packages.db.repository;

import com.example.demo.packages.db.entity.Author;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//        List<Author> getAuthorByFullName(String firstName, String lastName, String  middleName) {
//        Author author = new Author();
//        author.setFirstName(firstName);
//        author.setLastName(lastName);
//        author.setMiddleName(middleName);
//        Example<Author> example = Example.of(author);
//        return repository.findAll(example);
//    }
}
