package com.Literalura.Literalura.consola;

import com.Literalura.Literalura.modelo.Libro;
import com.Literalura.Literalura.servicio.GutendexService;
import com.Literalura.Literalura.servicio.AutorService;
import com.Literalura.Literalura.servicio.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LiteraluraConsola implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private LibroService libroservice;

    @Autowired
    private AutorService autorservice;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            ejecutarOpcion(opcion);
        } while (opcion != 0);
        System.out.println("¡Gracias por usar Literalura!");
    }

    private void mostrarMenu() {
        System.out.println("\nBienvenido a Literalura");
        System.out.println("1 - Buscar libro por título");
        System.out.println("2 - Listar libros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos en un determinado año");
        System.out.println("5 - Listar libros por idioma");
        System.out.println("0 - Salir");
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> buscarLibroPorTitulo();
            case 2 -> listarLibrosRegistrados();
            case 3 -> listarAutoresRegistrados();
            case 4 -> listarAutoresVivos();
            case 5 -> listarLibrosPorIdioma();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private void buscarLibroPorTitulo() {
        scanner.nextLine(); // Consumir el salto de línea pendiente
        String titulo = leerTexto("Ingrese el título o palabra clave del libro que desea buscar: ");
        gutendexService.buscarYGuardarLibros(titulo);
        System.out.println("Búsqueda completada y libros guardados en la base de datos.");
    }


    private void listarLibrosRegistrados() {
        List<Libro> libros = libroservice.obtenerTodosLosLibros();


        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            System.out.println("\nLibros registrados:\n");
            libros.forEach(libro -> {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
                System.out.println("---------------------------------------------------");
            });
        }
    }


    private void listarAutoresRegistrados() {
        autorservice.obtenerTodosLosAutores()
                .forEach(autor -> System.out.println(autor));
    }

    private void listarAutoresVivos() {
        int anio = leerEntero("Ingrese el año para buscar autores vivos: ");
        autorservice.obtenerAutoresVivosEnAnio(anio)
                .forEach(autor -> System.out.println(autor));
    }


    private void listarLibrosPorIdioma() {
        String idioma = leerTexto("Ingrese el idioma para filtrar libros (ejemplo: en, es): ");
        libroservice.obtenerLibrosPorIdioma(idioma)
                .forEach(libro -> System.out.println(libro));
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada no válida. Intente nuevamente: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.next();
    }
}


