
package com.Tucuman.libreriaConSpring.controladores;

import com.Tucuman.libreriaConSpring.entidades.Editorial;
import com.Tucuman.libreriaConSpring.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

@RequestMapping("/editorial")
public class EditorialController {
    
     @Autowired
    private EditorialServicio editorialServicio;
     
     @GetMapping("")
    public String listaDeAutores(Model model) {
        List<Editorial> editoriales = editorialServicio.listarEditorial();
        model.addAttribute("editoriales", editoriales);
        return "editorial-list";
}
    @GetMapping("/alta")
     public String alta(@RequestParam("id") String id) {
	try {
	    editorialServicio.altaEditorial(id);
	    return "redirect:/editorial";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/editorial";
	}
    }
    @GetMapping("/baja") // Baja
        public String delete(@RequestParam("id") String id) {
	try {
	    editorialServicio.borrarEditorial(id);
	    return "redirect:/editorial";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/editorial";
	}
    }
    
    
    
}