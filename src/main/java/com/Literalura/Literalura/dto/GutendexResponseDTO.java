package com.Literalura.Literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponseDTO {
    private int count; // Número total de resultados
    private List<LibroDTO> results; // Lista de libros

    // Getters y Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<LibroDTO> getResults() {
        return results;
    }

    public void setResults(List<LibroDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GutendexResponseDTO{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}


