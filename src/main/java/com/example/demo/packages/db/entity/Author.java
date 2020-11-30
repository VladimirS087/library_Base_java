package com.example.demo.packages.db.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id", nullable = false)
    private Long Id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Set<Book> books = new HashSet<Book>();


    /*Конструктор
     * */
    public Author(){

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
        return "Author{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", books=" + books +
                '}';
    }
}
