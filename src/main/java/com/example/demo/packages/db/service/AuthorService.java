package com.example.demo.packages.db.service;

import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Book;

import java.util.List;
import java.util.Set;

public interface AuthorService {

    List<Author> listAuthors ();

    Set<Book> authorBooks (Long id);

    Author addAuthor (Author author);

    Boolean deleteById(Long id);
}
