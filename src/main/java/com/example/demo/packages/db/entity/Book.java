package com.example.demo.packages.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id", nullable = false)
    private Long Id;

    @Column(name = "name")
    private String Name;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_genre_lnk",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Genre> genres;

    public void addBookGenre (Genre genre) {
        this.genres.add(genre);
        genre.getBooks().add(this);
    }

    public void removeBookGenre (Genre genre) {
        this.genres.remove(genre);
        genre.getBooks().remove(this);
    }
    @ManyToMany(mappedBy = "books")
    private Set<Person> persons;


    @Column(name = "author_id")
    private Long authorId;

    public Long getAuthor_id() { return authorId; }

    public void setAuthor_id(Long author_id) { this.authorId = authorId; }

    /*Конструктор
     * */
    public Book(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Person> getPersons() { return persons; }

    public Set<Genre> getGenres() { return genres; }

    public void setGenres(Set<Genre> genres) { this.genres = genres; }

    public void setPersons(Set<Person> persons) { this.persons = persons; }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", genres=" + genres +
                ", persons=" + persons +
                ", authorId=" + authorId +
                '}';
    }
}
