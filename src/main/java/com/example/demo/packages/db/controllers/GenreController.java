package com.example.demo.packages.db.controllers;

import com.example.demo.packages.db.entity.Genre;
import com.example.demo.packages.db.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "genres", produces = "application/json")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // просмотр всех жанров (без книг)
    @GetMapping(value = "/all-genres")
    public List<String> allGenres () {
        return genreService.allGenres();
    }

    // добавление нового жанра (без книги)
    @PostMapping(value = "add-genre")
    public Genre addGenre (@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

    // вывод статистики Жанр - количество книг
    @GetMapping(value = "/statistics-genre-books")
    public Map<String, Integer> genreBooksStatistics () {
        return genreService.genreBooksStatistics();
    }
}
