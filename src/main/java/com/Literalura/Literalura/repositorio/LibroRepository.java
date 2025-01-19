package com.Literalura.Literalura.repositorio;

import com.Literalura.Literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por idioma
    List<Libro> findByIdioma(String idioma);

    // Buscar libros por título
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}


