package com.example.demo.graphql;

import com.example.demo.model.Autor;
import com.example.demo.model.Libro;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LibroRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MutationResolver {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public MutationResolver(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @MutationMapping
    public Libro crearLibro(
            @Argument String titulo,
            @Argument Integer anio,
            @Argument("autorIds") java.util.List<Long> autorIds
    ) {
        Set<Autor> autores = new HashSet<>(autorRepository.findAllById(autorIds));
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setAutores(autores);
        // Sincroniza la relación bidireccional
        for (Autor autor : autores) {
            autor.getLibros().add(libro);
        }
        return libroRepository.save(libro);
    }

    @MutationMapping
    public Autor crearAutor(@Argument String nombre) {
        Autor autor = new Autor();
        autor.setNombre(nombre);
        return autorRepository.save(autor);
    }

    @MutationMapping
    public Boolean eliminarLibro(@Argument Long id) {
        if (!libroRepository.existsById(id)) return false;
        libroRepository.deleteById(id);
        return true;
    }

    @MutationMapping
    public Boolean eliminarAutor(@Argument Long id) {
        if (!autorRepository.existsById(id)) return false;
        autorRepository.deleteById(id);
        return true;
    }
}
