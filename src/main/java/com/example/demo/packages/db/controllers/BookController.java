package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.dto.BookDTO;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Genre;
import com.example.demo.packages.db.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/books", produces = "application/json")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) { this.bookService = bookService;
    }

    // добавляем книгу
    @PostMapping(value = "/add-book")
    public BookDTO addBook (@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    // удаляем по id, если у книги нет пользователя
    @DeleteMapping(value = "/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        if (bookService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    //  книге новый жанр
    @PutMapping(value = "/change-genre/{id}")
    public Book bookNewGenre(
            @PathVariable("id") Long id,
            @RequestBody Genre genre
    ) {
        return bookService.bookNewGenre(id, genre);
    }

    // список книг по автору
    @GetMapping(value = "/list-by-author")
    public Set<Book> booksByAuthor(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String middleName) {
        return bookService.booksByAuthor(firstName, lastName, middleName);
    }

    // список книг по жанру
    @GetMapping(value = "/list-by-genre")
    public List<String> booksByGenre(@RequestParam String genreName) {
        return bookService.booksByGenre(genreName);
    }

}
