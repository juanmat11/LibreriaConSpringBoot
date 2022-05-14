
package com.Tucuman.libreriaConSpring.controladores;

import com.Tucuman.libreriaConSpring.entidades.Autor;
import com.Tucuman.libreriaConSpring.entidades.Editorial;
import com.Tucuman.libreriaConSpring.entidades.Libro;
import com.Tucuman.libreriaConSpring.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroServicio libroServicio;
   

    @GetMapping("")
    public String crearLibro(Model modelo) {

        Libro libro = new Libro();
        libro.setAutor(new Autor());
        libro.setEditorial(new Editorial());
        modelo.addAttribute("libro", libro);
        return "libro-formulario";

    }

    @PostMapping("/save")
    public String guardarFormulario(@RequestParam("id") String id,
            @RequestParam("titulo") String titulo,
            @RequestParam(name = "anio", required = false) Integer anio,
            @RequestParam(name = "isbn", required = false) Long isbn,
            @RequestParam("nombreAutor") String nombreAutor,
            @RequestParam("nombreEditorial") String nombreEditorial,
            Model model) {
       

        Libro libro = new Libro();

        try {
            
            libro = libroServicio.crearLibro(id, isbn, titulo, anio, nombreAutor, nombreEditorial);

            model.addAttribute("libro", libro);

            return "libro-formulario";
        } catch (Exception ex) {
            ex.printStackTrace();
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);
            libro.setAnio(anio);
            Autor autor = new Autor();
            autor.setNombre(nombreAutor);
            libro.setAutor(autor);
            Editorial editorial = new Editorial();
            editorial.setNombre(nombreEditorial);
            libro.setEditorial(editorial);
            model.addAttribute("libro", libro);
            model.addAttribute("error", ex.getMessage());
            return "libro-formulario";
        }

    }

    @GetMapping("/list")
    public String listAll(Model modelo) { 
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("listaDeLibros", libros);
        return "libro-list";
    }

    @GetMapping("/modificar")
    public String formulario(@RequestParam(name = "libroId", required = true) String id, Model modelo) {
        Libro libro = libroServicio.buscarLibroPorId(id);
        modelo.addAttribute("libro", libro);
        return "libro-formulario";
    }
    @GetMapping("/eliminar")
    public String eliminarLibro(@RequestParam(name = "libroId", required = true) String id) {
        try {
            libroServicio.eliminarLibroPorId(id);
            
            return "redirect:/libro/list";
        } catch (Exception e) {
             e.printStackTrace();
             return "redirect:/libro/list";  
        }
    }
}
