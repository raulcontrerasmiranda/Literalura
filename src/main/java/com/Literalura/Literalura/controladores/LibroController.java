package com.Literalura.Literalura.controladores;

import com.Literalura.Literalura.servicio.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private GutendexService gutendexService;

    // Buscar y guardar libros desde la API
    @GetMapping("/importar")
    public String importarLibros(@RequestParam String titulo) {
        gutendexService.buscarYGuardarLibros(titulo);
        return "Libros importados correctamente.";
    }
}

