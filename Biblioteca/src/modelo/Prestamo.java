/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Date;
import modelo.Recurso;
import modelo.Usuario;

/**
 *
 * @author jhon
 */
public class Prestamo {
    private int ID;
    private Recurso recurso;
    private Usuario usuario;
    private String estado;
    private Date fechaRegistro, fechaLimite, fechaDevolucion;

    public Prestamo(int ID, Recurso recurso, Usuario usuario, String estado, Date fechaRegistro, Date fechaLimite, Date fechaDevolucion) {
        this.recurso = recurso;
        this.usuario = usuario;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaLimite = fechaLimite;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getID() {
        return ID;
    }
    
    

    public Recurso getRecurso() {
        return recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    
        
}
