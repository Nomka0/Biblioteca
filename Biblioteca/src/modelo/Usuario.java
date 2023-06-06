/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jhon
 */
public class Usuario {
    private int ID;
    private String nombre;
    private String correo;
    private long telefono;
    private String estamento;

    public Usuario(int ID, String nombre, String correo, long telefono, String estamento) {
        this.ID = ID;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.estamento = estamento;
    }

    public Usuario() {

    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getEstamento() {
        return estamento;
    }
    

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setEstamento(String estamento) {
        this.estamento = estamento;
    }
    
    
    
}
