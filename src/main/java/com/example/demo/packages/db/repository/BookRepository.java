package com.example.demo.packages.db.repository;

import com.example.demo.packages.db.entity.Author;
import com.example.demo.packages.db.entity.Book;
import com.example.demo.packages.db.entity.Genre;
import com.example.demo.packages.db.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT a FROM Author a WHERE first_name = :first_name AND last_name = :last_name AND middle_name = :middle_name")
    Author findAuthorByFullName(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("middle_name") String middleName
    );

    @Query("SELECT g FROM Genre g WHERE genre_name = :genre_name")
    Genre findBooksByGenre(
            @Param("genre_name") String genreName
    );
}
