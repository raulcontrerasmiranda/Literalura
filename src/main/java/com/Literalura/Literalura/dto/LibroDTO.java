package com.Literalura.Literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {
    private String title; // Título del libro
    private List<AutorDTO> authors; // Lista de autores
    private List<String> languages; // Idiomas disponibles

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AutorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AutorDTO> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "LibroDTO{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                '}';
    }
}
