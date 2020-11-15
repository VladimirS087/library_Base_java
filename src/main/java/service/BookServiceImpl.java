package service;

import model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookServiceImpl implements BookService {
    private static final List <Book> BOOK_REPOSITORY_LIST = new ArrayList<>();

    @Override
    public List <Book> post(Book book) {
        BOOK_REPOSITORY_LIST.add(book);
        return BOOK_REPOSITORY_LIST;
    }

    @Override
    public List<Book> get() {
        return BOOK_REPOSITORY_LIST;
    }

    @Override
    public List <Book> getAuthor(String author) {
        List <Book> booksThisAuthor = new ArrayList<>();
        for (Book book: BOOK_REPOSITORY_LIST) {
            if (author.equals(book.getAuthor())) {
                booksThisAuthor.add(book);
            }
        }
        return booksThisAuthor;
    }

    @Override
    public void deleteAuthorAndName(String author, String name) {
        for (Book book: BOOK_REPOSITORY_LIST) {
            if ((author.equals(book.getAuthor())) && (name.equals(book.getName()))) {
                BOOK_REPOSITORY_LIST.remove(BOOK_REPOSITORY_LIST.indexOf(book));
            }
        }
    }

}
