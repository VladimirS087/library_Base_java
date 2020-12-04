package com.example.demo.packages.db.service;

import com.example.demo.packages.db.entity.Genre;

import java.util.List;
import java.util.Map;

public interface GenreService {

   List<String> allGenres ();

   Genre addGenre (Genre genre);

   Map<String, Integer> genreBooksStatistics ();

}
