package com.Literalura.Literalura.servicio;

import com.Literalura.Literalura.client.GutendexClient;
import com.Literalura.Literalura.dto.LibroDTO;
import com.Literalura.Literalura.dto.GutendexResponseDTO;
import com.Literalura.Literalura.modelo.Autor;
import com.Literalura.Literalura.modelo.Libro;
import com.Literalura.Literalura.repositorio.AutorRepository;
import com.Literalura.Literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GutendexService {

    @Autowired
    private GutendexClient gutendexClient;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void buscarYGuardarLibros(String titulo) {
        System.out.println("Buscando libros con el título: " + titulo);

        // Llama a la API
        GutendexResponseDTO respuesta = gutendexClient.buscarLibrosPorTitulo(titulo);

        if (respuesta.getResults() == null || respuesta.getResults().isEmpty()) {
            System.out.println("No se encontraron resultados para: " + titulo);
            return;
        }

        // Procesa cada libro
        for (LibroDTO libroDTO : respuesta.getResults()) {
            System.out.println("Procesando libro: " + (libroDTO.getTitle() != null ? libroDTO.getTitle() : "Sin título"));

            // Maneja casos donde `autores` es null
            if (libroDTO.getAuthors() == null || libroDTO.getAuthors().isEmpty()) {
                System.out.println("El libro no tiene autores asociados: " + libroDTO.getTitle());
                continue; // Omite el libro si no tiene autores
            }

            // Buscar o guardar el autor
            Autor autor = libroDTO.getAuthors().stream()
                    .findFirst()
                    .map(authorDTO -> {
                        Autor existingAutor = autorRepository.findByNombre(authorDTO.getName());
                        if (existingAutor == null) {
                            Autor newAutor = new Autor();
                            newAutor.setNombre(authorDTO.getName());
                            newAutor.setFechaNacimiento(String.valueOf(authorDTO.getBirth_year()));
                            newAutor.setFechaMuerte(String.valueOf(authorDTO.getDeath_year()));
                            autorRepository.save(newAutor);
                            return newAutor;
                        }
                        return existingAutor;
                    })
                    .orElse(null);

            // Guardar el libro si hay un autor asociado
            if (autor != null) {
                Libro libro = new Libro();
                libro.setTitulo(libroDTO.getTitle() != null ? libroDTO.getTitle() : "Título desconocido");
                libro.setIdioma(libroDTO.getLanguages() != null && !libroDTO.getLanguages().isEmpty()
                        ? libroDTO.getLanguages().get(0)
                        : "Idioma desconocido");
                libro.setAutor(autor);
                libroRepository.save(libro);
                System.out.println("Libro guardado: " + libro.getTitulo());
            } else {
                System.out.println("No se pudo asociar un autor para el libro: " + libroDTO.getTitle());
            }
        }
    }

}
