package service;

import model.Book;

import java.util.List;

public interface BookService {
    List<Book> post(Book book);

    List<Book> get();

    List <Book> getAuthor(String author);

   void deleteAuthorAndName(String author, String name);

}
