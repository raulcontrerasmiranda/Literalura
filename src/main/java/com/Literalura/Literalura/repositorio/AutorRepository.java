package com.Literalura.Literalura.repositorio;

import com.Literalura.Literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaMuerteGreaterThanEqual(String inicio, String fin);
    Autor findByNombre(String nombre);
}







