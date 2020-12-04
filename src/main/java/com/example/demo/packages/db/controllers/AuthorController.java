package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/authors", produces = "application/json")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) { this.authorService = authorService;
    }

    // список авторов
    @GetMapping(value = "/list-authors")
    public List<Author> listAuthors () { return authorService.listAuthors();
    }

    // список книг автора
    @GetMapping(value = "/author-books/{id}")
    public Set<Book> authorBooks (@PathVariable("id") Long id) {
        return authorService.authorBooks(id);
    }

    // добавить автора
    @PostMapping(value = "/add-author")
    public Author addAuthor (@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    // удаляем по id, если у автора нет книг
    @DeleteMapping(value = "/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        if (authorService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

}
