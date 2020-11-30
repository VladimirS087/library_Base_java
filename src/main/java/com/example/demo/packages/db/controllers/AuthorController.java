package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Person;
import com.example.demo.packages.db.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // список авторов
    @GetMapping(value = "allAuthors")
    List<Author> allAuthors () {
        return authorRepository.findAll();
    }

    // список книг автора
    @GetMapping(value = "author-books/{Id}")
    Set<Book> authorBooks (@PathVariable("Id") Long Id) {
        Optional<Author> author = authorRepository.findById(Id);
        Author realAuthor = author.get();
        return realAuthor.getBooks();
    }

    // добавить автора
    @PostMapping(value = "post-author")
    public Author postAuthor (@RequestBody Author author) {
        return authorRepository.save(author);
    }

    // удаляем по Id, если у автора нет книг
    @DeleteMapping(value = "author/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestParam Long Id) {
        Optional<Author> author = authorRepository.findById(Id);
        Author realAuthor = author.get();
        if (realAuthor.getBooks().isEmpty()) {
            authorRepository.deleteById(Id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

}
