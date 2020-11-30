package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Genre;
import com.example.demo.packages.db.entity.Person;
import com.example.demo.packages.db.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // добавляем книгу
    @PostMapping(value = "post-book")
    public String postBook (@RequestBody Book book) {
        return bookRepository.save(book).toString();
    }

    // удаляем по Id, если у книги нет пользователя
    @DeleteMapping(value = "book/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestParam Long Id) {
        Optional<Book> book = bookRepository.findById(Id);
        Book realBook = book.get();
        if (realBook.getPersons().isEmpty()) {
            bookRepository.deleteById(Id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    //  книге новый жанр
    @PutMapping(value = "change-bookGenre/{Id}")
    public Book book(
            @PathVariable("Id") Book bookFromDb,
            @RequestBody Genre genre
    ) {
        bookFromDb.addBookGenre(genre);
        return bookRepository.save(bookFromDb);
    }


    // список книг по автору
    @GetMapping(value = "books-by-author")
    public Set<Book> booksByAuthor(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String middleName) {
        Author author = bookRepository.findAuthorByFullName(firstName, lastName, middleName);

        return author.getBooks();
    }

    // список книг по жанру
    @GetMapping(value = "books-by-genre")
    public List<String> booksByGenre(@RequestParam String genreName) {
        List<String> booksByGenre = new ArrayList<>();
        Genre genre = bookRepository.findBooksByGenre(genreName);
        booksByGenre.add("Genre : " + genre.getGenreName() + "Books : " + genre.getBooks());
        return booksByGenre;
    }

}
