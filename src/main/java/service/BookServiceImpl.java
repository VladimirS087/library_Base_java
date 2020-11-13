package service;

import model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookServiceImpl implements BookService {
    private static final Map<Integer, Book> BOOK_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger BOOK_ID_HOLDER = new AtomicInteger();

    @Override
    public String post(Book book) {
        final int bookId = BOOK_ID_HOLDER.incrementAndGet();
        book.setId(bookId);
        BOOK_REPOSITORY_MAP.put(bookId, book);
        return BOOK_REPOSITORY_MAP.toString();
    }

    @Override
    public List<Book> get() {
        return new ArrayList<>(BOOK_REPOSITORY_MAP.values());
    }

    @Override
    public Book read(int id) {
        return BOOK_REPOSITORY_MAP.get(id);
    }

    @Override
    public Book readAuthor(String author) {
        return BOOK_REPOSITORY_MAP.get(author);
    }


    @Override
    public boolean deleteAuthor(String author) {
        return BOOK_REPOSITORY_MAP.remove(author) != null;
    }

    @Override
    public boolean deleteName(String name) {
        return BOOK_REPOSITORY_MAP.remove(name) != null;
    }
}
