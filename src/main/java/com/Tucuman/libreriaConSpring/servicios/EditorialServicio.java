package com.Tucuman.libreriaConSpring.servicios;

import com.Tucuman.libreriaConSpring.entidades.Editorial;
import com.Tucuman.libreriaConSpring.repositorios.EditorialRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepository editorialRepository;

    public Editorial crearEditorial(String nombre) throws Exception {
        if (nombre == null) {
            throw new Exception("No se encontro la editorial");
        }
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);

        editorialRepository.save(editorial);
        return editorial;
    }
    
    public Editorial modificarEditorial(String id, String nombre) throws Exception {
        if (nombre == null) {
            throw new Exception("no se encontro la editorial");
        }
        Optional<Editorial> respuesta = editorialRepository.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            editorialRepository.save(editorial);
            return editorial;
            }
        return null;
    }
    public List<Editorial> listarEditorial() {
        return editorialRepository.listaDeEditoriales();
    }
//    public List<Editorial> listarEditorial() {
//        return editorialRepository.findAll();
//    }

    public void eliminarEditorial(String id, String nombre) throws Exception {
        if (nombre == null) {
            throw new Exception("no se encontro la editorial");
        }
        Optional<Editorial> respuesta = editorialRepository.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorialRepository.delete(editorial);
        }
        
        
    }
    public void borrarEditorial(String id) throws Error {
	Optional<Editorial> respuesta = editorialRepository.findById(id);
	if (respuesta.isPresent()) {
	    Editorial editorial = respuesta.get();
	    editorial.setAlta(false);
	 editorialRepository.save(editorial);
	} else {
	    throw new Error("No se encontró una editorial con ese nombre");
	}
    }

    public void altaEditorial(String id) throws Error {
	Optional<Editorial> respuesta = editorialRepository.findById(id);
	if (respuesta.isPresent()) {
	    Editorial editorial = respuesta.get();
	    editorial.setAlta(true);
	    editorialRepository.save(editorial);
	} else {
	    throw new Error("No se encontró un autor con ese nombre");
	}

}
}