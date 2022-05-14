package com.Tucuman.libreriaConSpring.servicios;

import com.Tucuman.libreriaConSpring.entidades.Autor;
import com.Tucuman.libreriaConSpring.entidades.Editorial;
import com.Tucuman.libreriaConSpring.entidades.Libro;
import com.Tucuman.libreriaConSpring.repositorios.LibroRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    LibroRepository libroRepository;
    @Autowired
    AutorServicio autorServicio;
    @Autowired
    EditorialServicio editorialServicio;

    public Libro crearLibro(String id, Long isbn, String titulo, Integer anio, String nombreAutor, String nombreEditorial) throws Exception {
        validar(isbn, titulo, anio, nombreAutor, nombreEditorial);

        Libro libro = new Libro();
        if (id != null && !id.isEmpty()) {
            libro.setId(id);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setIsbn(isbn);
            Libro libroDB = buscarLibroPorId(id);
            libroDB.getAutor().setNombre(nombreAutor);
            libroDB.getEditorial().setNombre(nombreEditorial);

            libro.setAutor(libroDB.getAutor());
            libro.setEditorial(libroDB.getEditorial());
        } else {
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setIsbn(isbn);
            libro.setAutor(autorServicio.crearAutor(nombreAutor));
            libro.setEditorial(editorialServicio.crearEditorial(nombreEditorial));
        }
        libro.setAlta(true);
        libro.setEjemplares(50);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(50);

        return libroRepository.save(libro);
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, String nombreAutor, String nombreEditorial) throws Exception {
        validar(isbn, titulo, anio, nombreAutor, nombreEditorial);

        Optional<Libro> respuesta = libroRepository.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setIsbn(isbn);
            libro.setAutor(autorServicio.modificarAutor(id, nombreAutor));
            libro.setEditorial(editorialServicio.modificarEditorial(id, nombreAutor));

            libroRepository.save(libro);
        } else {
            throw new Exception("No se encontro el Libro");
        }
    }

    public void eliminarLibro(String id, Long isbn, String titulo, Integer anio, String nombreAutor, String nombreEditorial) throws Exception {
        validar(isbn, titulo, anio, nombreAutor, nombreEditorial);

        Optional<Libro> respuesta = libroRepository.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libroRepository.delete(libro);
        } else {
            throw new Exception("No se encontro el Libro");
        }
    }

    public void validar(Long isbn, String titulo, Integer anio, String nombreAutor, String nombreEditorial) throws Exception {
        if (isbn == null) {
            throw new Exception("El ISBN no puede ser nulo o estar vacio");
        }
        if (titulo == null) {
            throw new Exception("El titulo no puede ser nulo o estar vacio");
        }
        if (anio == null) {
            throw new Exception("El año no puede ser nulo o estar vacio");
        }
        if (nombreAutor == null) {
            throw new Exception("El Autor no puede ser nulo o estar vacio");
        }
        if (nombreEditorial == null) {
            throw new Exception("La editorial no puede ser nulo o estar vacio");
        }
    }

    public Libro buscarLibroPorId(String id) {
        return libroRepository.getById(id);
    }

    public void eliminarLibroPorId(String id) {
        Optional<Libro> respuesta = libroRepository.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libroRepository.delete(libro);
        }
    }
}
