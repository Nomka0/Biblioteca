/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Recurso;
import modelo.Usuario;

/**
 *
 * @author jhon
 */
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
    public Recurso obtenerRecursoPorID(int ID) {
        for (Recurso recurso : recursos) {
            if (recurso.getID() == ID) {
                return recurso;
            }
        }
        return null;
    }

    @Override
    public List<Recurso> obtenerTodosLosRecursos() {
        return recursos;
    }

    @Override
    public void actualizarRecurso(Recurso recurso) {
        for (Recurso r : recursos) {
            if (r.getID() == recurso.getID()) {
                r.setTitulo(recurso.getTitulo());
                r.setAutor(recurso.getAutor());
                r.setGenero(recurso.getGenero());
                r.setTipoRecurso(recurso.getTipoRecurso());
                break;
            }
        }
    }

    @Override
    public void eliminarRecurso(int ID) {
        recursos.removeIf(recurso -> recurso.getID() == ID);
    }
    
    
}
