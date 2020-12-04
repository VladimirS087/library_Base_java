package com.example.demo.packages.db.service;

import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors (){
        return authorRepository.findAll();
    }

    @Override
    public Set<Book> authorBooks (Long id){
        Optional<Author> author = authorRepository.findById(id);
        Author realAuthor = author.get();
        return realAuthor.getBooks();
    }

    @Override
    public Author addAuthor (Author author){
        return authorRepository.save(author);
    }

    @Override
    public Boolean deleteById(Long id){
        Optional<Author> author = authorRepository.findById(id);
        Author realAuthor = author.get();
        if (realAuthor.getBooks().isEmpty()) {
            authorRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
