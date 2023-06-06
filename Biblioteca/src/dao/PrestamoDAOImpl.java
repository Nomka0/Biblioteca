/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import java.util.List;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
/**
 *
 * @author jhon
 */
public class PrestamoDAOImpl implements PrestamoDAO {
    private List<Prestamo> prestamos;
    
    public PrestamoDAOImpl() {
        this.prestamos = new ArrayList<>();
    }

    @Override
    public void crearPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    @Override
    public Prestamo obtenerPrestamo(int index) {
        
       return prestamos.get(index);
        
    }

    @Override
    public List<Prestamo> obtenerTodosLosPrestamos() {
        
        return prestamos;

    }

    @Override
    public void actualizarPrestamo(int index, Prestamo prestamoAct){
        prestamos.set(index, prestamoAct);
    }

    @Override
    public void eliminarPrestamo(int index) {
        prestamos.remove(index);
    }

     
}
