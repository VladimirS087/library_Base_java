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
    public List <Book> post(@RequestBody Book book) {
        return bookService.post(book);
    }

    @GetMapping(value = "/books")
    public List<Book> get() {
        return bookService.get();
    }

    @GetMapping(value = "/books/{author}")
    public List <Book> getAuthor(@PathVariable(name = "author") String author) {
        return bookService.getAuthor(author);
    }

    @DeleteMapping(value = "/books")
    public ResponseEntity<?> deleteAuthorAndName(@RequestParam String author, String name) {
        bookService.deleteAuthorAndName(author, name);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
