package com.example.demo.packages.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String Name;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_genre_lnk",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Genre> genres;

    @ManyToMany(mappedBy = "books")
    private Set<Person> persons;


    @Column(name = "author_id")
    private Long author_id;

    public Long getAuthor_id() { return author_id; }

    public void setAuthor_id(Long author_id) { this.author_id = author_id; }

    /*Конструктор
     * */
    public Book(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", genres=" + genres +
                ", persons=" + persons +
                ", authorId=" + author_id +
                '}';
    }
}
