package com.Literalura.Literalura.controladores;

import com.Literalura.Literalura.modelo.Autor;
import com.Literalura.Literalura.servicio.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> obtenerAutores() {
        return autorService.obtenerTodosLosAutores();
    }

    @PostMapping
    public Autor agregarAutor(@RequestBody Autor autor) {
        return autorService.guardarAutor(autor);
    }

    @GetMapping("/autores/vivos/{anio}")
    public List<Autor> autoresVivosEnAño(@PathVariable int anio) {
        return autorService.obtenerAutoresVivosEnAnio(anio);
    }

}
