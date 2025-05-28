package com.example.demo;

import com.example.demo.model.Autor;
import com.example.demo.model.Libro;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    public DemoDataLoader(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public void run(String... args) {
        // Autores
        Autor autor1 = new Autor();
        autor1.setNombre("Gabriel García Márquez");
        Autor autor2 = new Autor();
        autor2.setNombre("Isabel Allende");
        Autor autor3 = new Autor();
        autor3.setNombre("Julio Cortázar");
        Autor autor4 = new Autor();
        autor4.setNombre("Mario Vargas Llosa");

        autorRepository.save(autor1);
        autorRepository.save(autor2);
        autorRepository.save(autor3);
        autorRepository.save(autor4);

        // Libros
        Libro libro1 = new Libro();
        libro1.setTitulo("Cien años de soledad");
        libro1.setAnio(1967);
        libro1.setAutores(new HashSet<>(Set.of(autor1)));

        Libro libro2 = new Libro();
        libro2.setTitulo("La casa de los espíritus");
        libro2.setAnio(1982);
        libro2.setAutores(new HashSet<>(Set.of(autor2)));

        Libro libro3 = new Libro();
        libro3.setTitulo("Rayuela");
        libro3.setAnio(1963);
        libro3.setAutores(new HashSet<>(Set.of(autor3)));

        Libro libro4 = new Libro();
        libro4.setTitulo("Conversación en La Catedral");
        libro4.setAnio(1969);
        libro4.setAutores(new HashSet<>(Set.of(autor4)));

        Libro libro5 = new Libro();
        libro5.setTitulo("Libro colaborativo");
        libro5.setAnio(2024);
        libro5.setAutores(new HashSet<>(Set.of(autor1, autor2)));

        libroRepository.save(libro1);
        libroRepository.save(libro2);
        libroRepository.save(libro3);
        libroRepository.save(libro4);
        libroRepository.save(libro5);
    }
}
