package com.example.demo.packages.db.dto;

public class GenreDTO {

    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public GenreDTO(){

    }
    public GenreDTO(String genreName) {
        this.genreName = genreName;
    }
}
