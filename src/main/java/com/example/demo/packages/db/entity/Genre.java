package com.example.demo.packages.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "GENRES")
public class Genre {

    @Id
    @GeneratedValue
    @Column(name = "genre_id", nullable = false)
    private Long Id;

    @Column(name = "genre_name")
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;


    /*Конструктор
     * */
    public Genre(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Set<Book> getBooks() { return books; }

    public void setBooks(Set<Book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Genre{" +
                "Id=" + Id +
                ", genreName='" + genreName + '\'' +
                ", books=" + books +
                '}';
    }
}