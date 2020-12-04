package com.example.demo.packages.db.dto;

public class PersonTookBook {

    private PersonDTO personDTO;

    private BookDTO bookDTO;

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public PersonTookBook() {
    }

    public PersonTookBook(PersonDTO personDTO, BookDTO bookDTO) {
        this.personDTO = personDTO;
        this.bookDTO = bookDTO;
    }
}
