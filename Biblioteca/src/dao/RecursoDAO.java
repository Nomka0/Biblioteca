/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Recurso;

/**
 *
 * @author jhon
 */
public interface RecursoDAO {
    
    void crearRecurso(Recurso recurso);
    
    Recurso obtenerRecurso(int index);
  
    List<Recurso> obtenerTodosLosRecursos();
   
    void actualizarRecurso(int index,Recurso Recurso);
   
    void eliminarRecurso(int index);
}
