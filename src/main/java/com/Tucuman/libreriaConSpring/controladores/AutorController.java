/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tucuman.libreriaConSpring.controladores;

import com.Tucuman.libreriaConSpring.entidades.Autor;
import com.Tucuman.libreriaConSpring.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorServicio autorServicio;

//    @GetMapping("")
//    public String formulario(Model model) {
//        Autor autor = new Autor();
//        model.addAttribute("autor", autor);
//        return "autor-formulario";
//    }
    @GetMapping("")
    public String listaDeAutores(Model model) {
        List<Autor> autores = autorServicio.listarAutor();
        model.addAttribute("autores", autores);
        return "autor-list";
    }

@GetMapping("/alta")
        public String alta(@RequestParam("id") String id) {
	try {
	    autorServicio.altaAutor(id);
	    return "redirect:/autor";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/autor";
	}
    }

    @GetMapping("/delete") // Baja
        public String delete(@RequestParam("id") String id) {
	try {
	    autorServicio.borrarAutor(id);
	    return "redirect:/autor";
	} catch (Exception e) {
	    e.printStackTrace();
	    return "redirect:/autor";
	}
    }
    
    @GetMapping("/eliminar")
        public String eliminarAutor(@RequestParam(name = "id", required = true) String id){
        try {
            autorServicio.eliminarAutor(id);
            return "redirect:/autor";
        } catch (Exception e) {
             e.printStackTrace();
             return "redirect:/autor";  
        }
        
       }

}