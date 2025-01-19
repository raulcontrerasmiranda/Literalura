package com.Literalura.Literalura.servicio;

import com.Literalura.Literalura.modelo.Autor;
import com.Literalura.Literalura.repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        String anioStr = String.valueOf(anio);
        return autorRepository.findByFechaNacimientoLessThanEqualAndFechaMuerteGreaterThanEqual(anioStr, anioStr);
    }


}
