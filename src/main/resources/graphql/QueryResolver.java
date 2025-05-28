package com.example.demo.graphql;

import com.example.demo.model.Autor;
import com.example.demo.model.Libro;
import com.example.demo.service.AutorService;
import com.example.demo.service.LibroService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {
    private final LibroService libroService;
    private final AutorService autorService;

    public QueryResolver(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    @QueryMapping
    public List<Libro> libros() {
        return libroService.findAll();
    }

    @QueryMapping
    public Libro libro(Long id) {
        return libroService.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Autor> autores() {
        return autorService.findAll();
    }

    @QueryMapping
    public Autor autor(Long id) {
        return autorService.findById(id).orElse(null);
    }
}
