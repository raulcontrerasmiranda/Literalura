package com.Literalura.Literalura.servicio;

import com.Literalura.Literalura.modelo.Libro;
import com.Literalura.Literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Método para obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    // Método para guardar un libro
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Método para buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Método para buscar libros por idioma
    public List<Libro> buscarPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    // Alias para buscar libros por idioma
    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}

