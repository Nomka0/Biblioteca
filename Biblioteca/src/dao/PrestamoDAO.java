/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;

/**
 *
 * @author jhon
 */
public interface PrestamoDAO {
    
     void crearPrestamo(Prestamo prestamo);
    
     Prestamo obtenerPrestamo(int index);
   
     List<Prestamo> obtenerTodosLosPrestamos();
    
     void actualizarPrestamo(int index,Prestamo prestamo);
    
     void eliminarPrestamo(int index);
}

