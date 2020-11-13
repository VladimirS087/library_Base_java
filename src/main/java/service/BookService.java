package service;

import model.Book;

import java.util.List;

public interface BookService {
    String post(Book book);

    List<Book> get();

    Book read(int id);

    Book readAuthor(String author);

    boolean deleteAuthor(String author);

    boolean deleteName(String name);
}
