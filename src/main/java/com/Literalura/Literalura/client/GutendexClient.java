package com.Literalura.Literalura.client;

import com.Literalura.Literalura.dto.GutendexResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GutendexClient {

    private final RestTemplate restTemplate;

    // Endpoint base de Gutendex
    private static final String BASE_URL = "https://gutendex.com/books";

    public GutendexClient() {
        this.restTemplate = new RestTemplate();
    }

    // Buscar libros por título
    public GutendexResponseDTO buscarLibrosPorTitulo(String titulo) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("gutendex.com")
                .path("/books")
                .queryParam("search", titulo)
                .toUriString();

        System.out.println("URL de la solicitud: " + url);

        // Realiza la solicitud HTTP GET
        return restTemplate.getForObject(url, GutendexResponseDTO.class);
    }

}

