package com.example.demo.packages.db.service;

import com.example.demo.packages.db.entity.Genre;
import com.example.demo.packages.db.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<String> allGenres (){
         List<Genre> genres = genreRepository.findAll();
         List<String> genresWithoutBooks = new ArrayList<String>();
         for(Genre genre : genres) {
             genresWithoutBooks.add(genre.getGenreName());
         }
        return genresWithoutBooks;
    }

    @Override
    public Genre addGenre (Genre genre){
        return genreRepository.save(genre);
    }

    @Override
    public Map<String, Integer> genreBooksStatistics (){
        HashMap<String, Integer> genreBooksStatistics = new HashMap<String, Integer>();
        List<Genre> genres = genreRepository.findAll();
        for (Genre genre : genres) {
            genreBooksStatistics.put(genre.getGenreName(), genre.getBooks().size());
        }
        return genreBooksStatistics;
    }

}
