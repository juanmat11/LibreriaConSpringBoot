/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tucuman.libreriaConSpring.repositorios;

import com.Tucuman.libreriaConSpring.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String>{
    
//     @Query("select a from Libro a where a.titulo = :titulo")
//    public Libro buscarLibroPorTitulo(@Param("titulo") String titulo);


}
