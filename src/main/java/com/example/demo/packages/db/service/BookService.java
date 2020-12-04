package com.example.demo.packages.db.service;

import com.example.demo.packages.db.dto.BookDTO;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Genre;

import java.util.List;
import java.util.Set;

public interface BookService {

    BookDTO addBook(BookDTO bookDTO);

    Boolean deleteById(Long id);

    Book bookNewGenre (Long id, Genre genre);

    Set<Book> booksByAuthor(String firstName, String lastName, String middleName);

    List<String> booksByGenre(String genreName);
}
