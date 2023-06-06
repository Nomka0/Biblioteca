/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Recurso;

public class RecursoDAOImpl implements RecursoDAO {

    private List<Recurso> recursos;

    public RecursoDAOImpl() {
        this.recursos = new ArrayList<>();
    }

    @Override
    public void crearRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    @Override
    public Recurso obtenerRecurso(int index) {
        return recursos.get(index);
    }

    @Override
    public List<Recurso> obtenerTodosLosRecursos() {
        return recursos;
    }
    
    @Override 
    public void actualizarRecurso(int index, Recurso RecursoAct){
        recursos.set(index, RecursoAct);
    }

    @Override
    public void eliminarRecurso(int index) {
        recursos.remove(index);
    }
}