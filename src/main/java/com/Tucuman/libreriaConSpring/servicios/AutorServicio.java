package com.Tucuman.libreriaConSpring.servicios;

import com.Tucuman.libreriaConSpring.entidades.Autor;
import com.Tucuman.libreriaConSpring.repositorios.AutorRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    AutorRepository autorRepository;

    public Autor crearAutor(String nombre) throws Exception {

        if (nombre == null) {
            throw new Exception("No se encontro el autor");
        }
        Autor autor = new Autor();
        autor = autorRepository.buscarAutorPorNombre(nombre);
        if (autor == null) {
            autor.setNombre(nombre);
            autor.setAlta(true);
            autorRepository.save(autor);
            return autor;
        }

        return autor;

    }

    public Autor modificarAutor(String id, String nombre) throws Exception {
        if (nombre == null) {
            throw new Exception("no se encontro el autor");
        }
        Optional<Autor> respuesta = autorRepository.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepository.save(autor);
            return autor;
        }
        return null;
    }

    public List<Autor> listarAutor() {
        return autorRepository.findAll();
    }

    public void eliminarAutor(String id) throws Exception {
//        if (nombre == null) {
//            throw new Exception("no se encontro el autor");
//        }
        Optional<Autor> respuesta = autorRepository.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autorRepository.delete(autor);
        }

    }

    public Autor buscarAutorPorId(String id) {
        return autorRepository.getById(id);
    }

    public void borrarAutor(String id) throws Error {
        Optional<Autor> respuesta = autorRepository.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
            autorRepository.save(autor);
        } else {
            throw new Error("No se encontró un autor con ese nombre");
        }
    }

    public void altaAutor(String id) throws Error {
        Optional<Autor> respuesta = autorRepository.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(true);
            autorRepository.save(autor);
        } else {
            throw new Error("No se encontró un autor con ese nombre");
        }
    }
//    public void eliminarAutor(String id) throws Error{
//        Autor autor = autorRepository.getById(id);
//        if(autor == null){
//            throw new Error("El autor no existe en la base de datos");
//        }else{
//            autorRepository.delete(autor);
//        }
//    }

}
