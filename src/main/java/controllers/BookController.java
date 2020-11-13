package controllers;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookService;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/books")
    public ResponseEntity<?> post(@RequestBody Book book) {
        bookService.post(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> get() {
        final List<Book> books = bookService.get();

        return books != null &&  !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> read(@PathVariable(name = "id") int id) {
        final Book book = bookService.read(id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/books/{author}")
    public ResponseEntity<Book> readAuthor(@PathVariable(name = "author") String author) {
        final Book book = bookService.readAuthor(author);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/books/{author}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(name = "author") String author) {
        final boolean deleted = bookService.deleteAuthor(author);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/books/{name}")
    public ResponseEntity<?> deleteName(@PathVariable(name = "name") String name) {
        final boolean deleted = bookService.deleteName(name);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
