package com.example.demo.packages.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERSONS")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "person_id", nullable = false)
    private Long Id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "library_card",
            joinColumns = @JoinColumn(name = "book_book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_genre_id")
    )
    private Set<Book> books;

    public void addBook (Book book) {
        this.books.add(book);
        book.getPersons().add(this);
    }

    public void removeBook (Book book) {
        this.books.remove(book);
        book.getPersons().remove(this);
    }
    /*Конструктор
     * */
    public Person(){

    }
    public Person(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Set<Book> getBooks() { return books; }

    public void setBooks(Set<Book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", books=" + books +
                '}';
    }
}
