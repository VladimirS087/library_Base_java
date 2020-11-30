package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Genre;
import com.example.demo.packages.db.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class GenreController {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    // просмотр всех жанров (без книг)

    @GetMapping(value = "allGenres")
    List<Genre> allGenres () {
        return genreRepository.findAll();
    }

    // добавление нового жанра (без книги)
    @PostMapping(value = "post-genre")
    public Genre postGenre (@RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    // вывод статистики Жанр - количество книг
    @GetMapping(value = "genre-books-number")
    Map<String, Integer> genreBooksNumber () {
        HashMap<String, Integer> genreBooksNumber = new HashMap<String, Integer>();
        List<Genre> genres = genreRepository.findAll();
        for (Genre genre : genres) {
            genreBooksNumber.put(genre.getGenreName(), genre.getBooks().size());
        }
        return genreBooksNumber;
    }
}
