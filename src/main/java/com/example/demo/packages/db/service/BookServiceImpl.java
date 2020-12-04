package com.example.demo.packages.db.service;

import com.example.demo.packages.db.dto.BookDTO;
import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Genre;
import com.example.demo.packages.db.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO addBook (BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        bookRepository.save(book);
        return bookDTO;
    }

    @Override
    public Boolean deleteById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        Book realBook = book.get();
        if (realBook.getPersons().isEmpty()) {
            bookRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Book bookNewGenre(Long id, Genre genre){
       Book book = bookRepository.findById(id).get();
       book.getGenres().clear();
       book.getGenres().add(genre);
       return bookRepository.save(book);
    }

    @Override
    public Set<Book> booksByAuthor(String firstName, String lastName, String middleName){
        Author author = bookRepository.findAuthorByFullName(firstName, lastName, middleName);
        return author.getBooks();
    }

    @Override
    public List<String> booksByGenre(String genreName){
        List<String> booksByGenre = new ArrayList<>();
        Genre genre = bookRepository.findBooksByGenre(genreName);
        booksByGenre.add("Genre : " + genre.getGenreName() + "Books : " + genre.getBooks());
        return booksByGenre;
    }




}

